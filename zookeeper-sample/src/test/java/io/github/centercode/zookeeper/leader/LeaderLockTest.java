package io.github.centercode.zookeeper.leader;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.zookeeper.CreateMode.EPHEMERAL_SEQUENTIAL;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryForever;
import org.apache.curator.utils.ZKPaths;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 没有mock ZooKeeper，需手动执行
 */
@Ignore
public class LeaderLockTest {

    String zooKeeperConnectStr = "localhost:2181";
    String lockPathMock = "/root";
    String lockNameMock = "lock-";
    String instanceIdMock = "host01";

    @Test
    public void testWorkflow() throws Exception {
        try (CuratorFramework client = createCuratorFramework();
             LeaderLock lock = new LeaderLock(client, lockPathMock, lockNameMock, instanceIdMock)) {
            client.start();
            lock.start();
            // first node get leadership
            assertHasLeadership(lock, true);
            String externalPath = client.create()
                .creatingParentContainersIfNeeded()
                .withProtection()
                .withMode(EPHEMERAL_SEQUENTIAL)
                .forPath(ZKPaths.makePath(lockPathMock, lockNameMock), instanceIdMock.getBytes(UTF_8));
            // second node doesn't affect this node's leadership
            assertHasLeadership(lock, true);
            String oldPath = lock.ourPath.get();
            // manually failover to second node
            client.delete().forPath(oldPath);
            // first node lost leadership
            assertHasLeadership(lock, false);
            Assert.assertNotEquals(oldPath, lock.ourPath.get());
            // manually failover back to first node
            client.delete().forPath(externalPath);
            // first node regain leadership
            assertHasLeadership(lock, true);
        }
    }

    @Test
    public void testLeaderLockName() throws Exception {
        try (CuratorFramework client = createCuratorFramework();
             LeaderLock lock = new LeaderLock(client, lockPathMock, lockNameMock, instanceIdMock)) {
            client.start();
            lock.start();
            assertHasLeadership(lock, true);
            String ourPath = lock.ourPath.get();
            String leaderNodeName = LeaderLock.getLeaderLockName(client, lockPathMock, lockNameMock);
            Assert.assertEquals(ourPath, lockPathMock + "/" + leaderNodeName);
            lock.close();
            leaderNodeName = LeaderLock.getLeaderLockName(client, lockPathMock, lockNameMock);
            Assert.assertNull(leaderNodeName);
        }
    }

    private CuratorFramework createCuratorFramework() {
        return CuratorFrameworkFactory.builder()
            .connectString(zooKeeperConnectStr)
            .retryPolicy(new RetryForever(5000))
            .connectionTimeoutMs(10_000)
            .sessionTimeoutMs(10_000)
            .build();
    }

    private void assertHasLeadership(LeaderLock locker, boolean expect) throws InterruptedException {
        // ZooKeeper可能会有延迟
        TimeUnit.SECONDS.sleep(5);
        Assert.assertEquals(expect, locker.hasLeadership());
    }
}
