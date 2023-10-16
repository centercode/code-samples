package io.github.centercode.zookeeper.ha.impl;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.curator.framework.imps.CuratorFrameworkState.STARTED;
import static org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode.BUILD_INITIAL_CACHE;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import io.github.centercode.zookeeper.ha.HAObserver;
import io.github.centercode.zookeeper.leader.LeaderLock;
import org.apache.commons.io.IOUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.locks.LockInternalsSorter;
import org.apache.curator.framework.recipes.locks.StandardLockInternalsDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

/**
 * A ZooKeeper based HAObserver implementation.
 */
class HAObserverZookeeperImpl implements HAObserver {

    private final static Logger LOG = LoggerFactory.getLogger(HAObserverZookeeperImpl.class);

    /**
     * 服务是否已经启动
     */
    boolean started;

    /**
     * LeaderLock 在ZooKeeper中节点的目录
     */
    String lockPath;

    /**
     * curatorClient实例
     */
    CuratorFramework curatorClient;

    /**
     * PathChildrenCache实例
     */
    PathChildrenCache pathChildrenCache;

    /**
     * 存储上一次 child 事件后的 Leader，用于对比 Leader 是否发生变更
     */
    AtomicReference<String> lastLeader = new AtomicReference<>();

    static final LockInternalsSorter sorter = StandardLockInternalsDriver::standardFixForSorting;

    HAObserverZookeeperImpl(CuratorFramework curatorClient, String lockPath) {
        this.lockPath = lockPath;
        this.curatorClient = curatorClient;
    }

    /**
     * start() 函数可重入
     */
    @Override
    public synchronized void start(BiConsumer<String, String> leaderChangeListener) throws Exception {
        Preconditions.checkState(curatorClient.getState() == STARTED,
            "curator client hasn't started.[lockPath='{}']", lockPath);
        Preconditions.checkState(!started, "HAObserver already started.[lockPath='{}']", lockPath);
        if (null != pathChildrenCache) {
            IOUtils.closeQuietly(pathChildrenCache);
        }
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorClient, lockPath, false);
        pathChildrenCache.getListenable().addListener((client, event) -> {
            switch (event.getType()) {
                case CHILD_ADDED:
                case CHILD_REMOVED:
                    LOG.trace("child nodes changed.[lockPath='{}', event='{}']", lockPath, event.getType());
                    String newLeader = getLeader();
                    String oldLeader = lastLeader.getAndSet(newLeader);
                    LOG.info("child nodes changed.[lockPath='{}', event='{}', old='{}', new='{}']",
                        lockPath, event.getType(), oldLeader, newLeader);
                    if (!Objects.equals(oldLeader, newLeader)) {
                        LOG.info("found leader changed.[lockPath='{}', old='{}', new='{}']", lockPath, oldLeader, newLeader);
                        leaderChangeListener.accept(oldLeader, newLeader);
                    }
                    break;
                default:
                    LOG.trace("unexpected nodes change event.[lockPath='{}', event='{}']", lockPath, event.getType());
                    break;
            }
        });
        // trigger initial event
        lastLeader.set(getLeader());
        if (null != lastLeader.get()) {
            leaderChangeListener.accept(null, lastLeader.get());
        }
        LOG.trace("HAObserver starting.[lockPath='{}']", lockPath);
        pathChildrenCache.start(BUILD_INITIAL_CACHE);
        LOG.info("HAObserver started.[lockPath='{}']", lockPath);
        this.pathChildrenCache = pathChildrenCache;
    }

    @Override
    public String getLeader() throws Exception {
        String lockName = LeaderLock.getLeaderLockName(curatorClient, lockPath, LeaderLock.LOCK_NAME);
        if (null == lockName) {
            return null;
        }
        byte[] data = curatorClient.getData().forPath(lockPath + "/" + lockName);
        String leader = new String(data, UTF_8).intern();
        LOG.trace("found leader.[lockPath='{}', leader='{}']", lockPath, leader);
        return leader;
    }

    @Override
    public synchronized void close() {
        if (started) {
            LOG.info("HAObserver close.[lockPath='{}']", lockPath);
            IOUtils.closeQuietly(
                pathChildrenCache,
                e -> LOG.error("error when close observer.[lockPath='{}']", lockPath, e)
            );
            this.pathChildrenCache = null;
            this.lastLeader.set(null);
            this.started = false;
        }
    }

    @VisibleForTesting
    String getLastLeader() {
        return lastLeader.get();
    }
}
