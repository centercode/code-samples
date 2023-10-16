package io.github.centercode.zookeeper.leader;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.CHILD_REMOVED;
import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.CONNECTION_LOST;
import static org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type.CONNECTION_SUSPENDED;
import static org.apache.zookeeper.CreateMode.EPHEMERAL_SEQUENTIAL;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ZKPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 实现Leader选举以及在Leader权限变更时的事件处理机制.
 * 相比于 {@link org.apache.curator.framework.recipes.leader.LeaderLatch}
 * 节点被删除后会自动重新创建，可以实现手动故障切换
 */
public class LeaderLock implements Closeable {
    private static final Logger LOG = LoggerFactory.getLogger(LeaderLock.class);
    public static final String LOCK_NAME = "lock-";
    private final String lockName;
    private final String id;
    private final String lockPath;
    private final CuratorFramework client;
    private final PathChildrenCache pathChildrenCache;
    private final List<LeaderLatchListener> listeners = new ArrayList<>();
    @VisibleForTesting
    final AtomicReference<String> ourPath = new AtomicReference<>();
    private final AtomicBoolean hasLeadership = new AtomicBoolean(false);

    public LeaderLock(CuratorFramework client, String lockPath, String id) {
        this(client, lockPath, LOCK_NAME, id);
    }

    @VisibleForTesting
    LeaderLock(CuratorFramework client, String lockPath, String lockName, String id) {
        this.id = Preconditions.checkNotNull(id, "id cannot be null");
        this.client = Preconditions.checkNotNull(client, "client cannot be null");
        this.lockPath = PathUtils.validatePath(lockPath);
        this.pathChildrenCache = new PathChildrenCache(client, lockPath, false);
        this.lockName = Preconditions.checkNotNull(lockName, "lockName cannot be null");
    }

    public synchronized void start() throws Exception {
        pathChildrenCache.start();
        // 注册监听事件
        pathChildrenCache.getListenable().addListener((client, event) -> {
            LOG.trace("found children change event.[lockPath='{}', event='{}']", lockPath, event.getType());
            nodeDeletedHandler(event);
            leadershipCheckHandler(event);
        });
        // 完成监听事件注册后，初始化对应节点
        createOurNode();
    }

    /**
     * 注册LeaderLatchListener，实现Leader变更情况下的事件处理
     */
    public void addListener(LeaderLatchListener listener) {
        this.listeners.add(listener);
    }

    /**
     * 当前LeaderLock是否拥有Leader权限
     */
    public boolean hasLeadership() {
        return hasLeadership.get();
    }

    /**
     * 获取ZooKeeper中以 lockPath 为目录的Leader节点的名称
     */
    public static String getLeaderLockName(CuratorFramework client, String lockPath, String lockName) throws Exception {
        List<String> children = Lists.newArrayList(client.getChildren().forPath(lockPath));
        children.sort(Comparator.comparing(str -> extractIndexFromNodeName(lockName, str)));
        return children.stream().findFirst().orElse(null);
    }

    @Override
    public void close() {
        try {
            deleteOurNode();
        } catch (Exception e) {
            LOG.error("failed to delete ournode.[ourPath='{}']", ourPath.get());
        }
        IOUtils.closeQuietly(this.pathChildrenCache);
    }

    /**
     * 节点被删除事件的Handler，当本节点被删除时自动重新创建
     */
    private void nodeDeletedHandler(PathChildrenCacheEvent event) throws Exception {
        PathChildrenCacheEvent.Type type = event.getType();
        if (type == CHILD_REMOVED) {
            if (Objects.equals(ourPath.get(), event.getData().getPath())) {
                // 本节点被删除，自动重新创建
                createOurNode();
            }
        }
    }

    /**
     * 连接丢失或者有节点增删事件的Handler，重新确定当前节点是否是Leader
     */
    private void leadershipCheckHandler(PathChildrenCacheEvent event) {
        PathChildrenCacheEvent.Type type = event.getType();
        if (type == CONNECTION_SUSPENDED || type == CONNECTION_LOST) {
            // 确定会丢失 leader 权限的事件
            setLeadership(false);
        } else {
            // 其他事件都要重新确认 leader 权限是否变更
            redetermineLeadership();
        }
    }

    /**
     * 在ZooKeeper中创建对应的临时有序节点,
     * 对应节点目录为/{@link LeaderLock#lockPath}，节点名以{@link LeaderLock#lockName}为前缀，
     * 存储的数据为{@link LeaderLock#id}
     */
    private void createOurNode() throws Exception {
        String path = client.create()
            .creatingParentContainersIfNeeded()
            .withProtection()
            .withMode(EPHEMERAL_SEQUENTIAL)
            .forPath(ZKPaths.makePath(lockPath, lockName), id.getBytes(UTF_8));
        this.ourPath.set(path);
        LOG.trace("created node path. [path='{}']", path);
    }

    /**
     * 重新检测当前节点是否是Leader，并设置状态到{@link LeaderLock#hasLeadership}
     */
    private void redetermineLeadership() {
        List<String> children;
        try {
            children = Lists.newArrayList(client.getChildren().forPath(lockPath));
        } catch (Exception e) {
            LOG.error("failed to get children.[lockPath='{}']", lockPath, e);
            setLeadership(false);
            return;
        }
        if (children.isEmpty()) {
            setLeadership(false);
            return;
        }
        children.sort(Comparator.comparing(this::extractIndexFromNodeName));
        LOG.trace("sorted children nodes.[lockPath='{}', children='{}'", lockPath, children);
        String leaderNodeName = children.get(0);
        setLeadership(ourPath.get() != null && ourPath.get().endsWith(leaderNodeName));
    }

    /**
     * 在ZooKeeper中删除对应的临时有序节点
     */
    private void deleteOurNode() throws Exception {
        String oldPath = ourPath.getAndSet(null);
        if (oldPath != null) {
            client.delete().guaranteed().forPath(oldPath);
        }
        setLeadership(false);
        LOG.trace("deleted node path.[oldPath='{}']", oldPath);
    }

    private synchronized void setLeadership(boolean newValue) {
        boolean oldValue = this.hasLeadership.getAndSet(newValue);
        LOG.trace("set leadership to {}.[lockPath='{}']", newValue, lockPath);
        if (oldValue && !newValue) {
            this.listeners.forEach(LeaderLatchListener::notLeader);
        } else if (!oldValue && newValue) {
            this.listeners.forEach(LeaderLatchListener::isLeader);
        }
    }

    Integer extractIndexFromNodeName(String nodeName) {
        return extractIndexFromNodeName(lockName, nodeName);
    }

    static Integer extractIndexFromNodeName(String lockName, String nodeName) {
        int i = nodeName.lastIndexOf(lockName);
        if (0 <= i && (i += lockName.length()) < nodeName.length()) {
            try {
                return Integer.parseInt(nodeName.substring(i));
            } catch (Exception e) {
                LOG.error("error when parse int.[str='{}']", nodeName.substring(i), e);
            }
        }
        // return invalid index
        return Integer.MAX_VALUE;
    }
}
