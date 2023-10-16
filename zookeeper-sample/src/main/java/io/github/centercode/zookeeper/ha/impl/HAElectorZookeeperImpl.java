package io.github.centercode.zookeeper.ha.impl;

import static org.apache.curator.framework.imps.CuratorFrameworkState.STARTED;

import com.google.common.base.Preconditions;
import io.github.centercode.zookeeper.ha.HAElector;
import io.github.centercode.zookeeper.leader.LeaderLock;
import org.apache.commons.io.IOUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A ZooKeeper based HAElector implementation.
 */
class HAElectorZookeeperImpl implements HAElector {

    private final static Logger LOG = LoggerFactory.getLogger(HAElectorZookeeperImpl.class);

    /**
     * 服务是否已经启动
     */
    boolean started;

    /**
     * LeaderLock 在ZooKeeper中节点的目录
     */
    String lockPath;

    /**
     * 负责Leader选举的锁实现
     */
    LeaderLock leaderLock;
    /**
     * 高可用资源的信息，由LeaderLock存储在ZooKeeper中对应节点的data中
     */
    String resource;

    /**
     * curatorClient实例
     */
    CuratorFramework curatorClient;

    HAElectorZookeeperImpl(CuratorFramework curatorClient, String lockPath, String resource) {
        this.started = false;
        this.lockPath = lockPath;
        this.curatorClient = curatorClient;
        this.resource = resource;
    }

    /**
     * start() 函数可重入
     */
    @Override
    public synchronized void start(Runnable takeLeadershipAction, Runnable leaveLeadershipAction) throws Exception {
        Preconditions.checkState(curatorClient.getState() == STARTED, "curator client hasn't started.[lockPath='{}']", lockPath);
        Preconditions.checkState(!started, "HAElector already started.[lockPath='{}']", lockPath);
        if (null != leaderLock) {
            IOUtils.closeQuietly(leaderLock);
        }
        this.leaderLock = new LeaderLock(curatorClient, lockPath, resource);
        this.leaderLock.addListener(new LeaderLatchListener() {
            @Override
            public void isLeader() {
                LOG.info("taken leadership.[lockPath='{}', resource='{}']", lockPath, resource);
                takeLeadershipAction.run();
            }

            @Override
            public void notLeader() {
                LOG.info("leaving leadership.[lockPath='{}', resource='{}']", lockPath, resource);
                leaveLeadershipAction.run();
            }
        });
        LOG.trace("HAElector starting.[lockPath='{}', resource='{}']", lockPath, resource);
        this.leaderLock.start();
        LOG.info("HAElector started.[lockPath='{}', resource='{}']", lockPath, resource);
        this.started = true;
    }

    @Override
    public boolean isLeader() {
        return started && leaderLock.hasLeadership();
    }

    @Override
    public synchronized void close() {
        if (started) {
            LOG.info("HAElector close.[lockPath='{}', resource='{}']", lockPath, resource);
            IOUtils.closeQuietly(
                this.leaderLock,
                e -> LOG.error("error when close elector.[lockPath='{}', resource='{}']", lockPath, resource, e)
            );
            this.leaderLock = null;
            this.started = false;
        }
    }
}
