package io.github.centercode.zookeeper.ha.impl;

import io.github.centercode.zookeeper.ha.HAElector;
import io.github.centercode.zookeeper.ha.HAProtocol;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@Ignore
public class HAObserverZookeeperImplTest {

    static String instanceIdMock = "host01:8888";

    String connectStringMock = "localhost:2181";

    String namespaceMock = "";

    Runnable noopRunnable = () -> {
    };

    BiConsumer<String, String> noopBiConsumer = (o, n) -> {
    };

    static class EventCounter implements BiConsumer<String, String> {

        int leaderAppearEvents = 0;

        int leaderLostEvents = 0;

        @Override
        public void accept(String oldLeader, String newLeader) {
            if (oldLeader == null && newLeader != null) {
                leaderAppearEvents++;
            } else if (oldLeader != null && newLeader == null) {
                leaderLostEvents++;
            }
        }

        void assertEvents(int leaderAppearEventsExpect, int leaderLostEventsExpect) {
            Assert.assertEquals(this.leaderAppearEvents, leaderAppearEventsExpect);
            Assert.assertEquals(this.leaderLostEvents, leaderLostEventsExpect);
        }
    }

    @Test
    public void testNoEventObserved() throws Exception {
        EventCounter eventCounter = new EventCounter();
        try (HAProtocol<ResourceTypeMock> protocol = HAProtocolZookeeperImpl.create(connectStringMock, namespaceMock, instanceIdMock)) {
            protocol.start();
            HAElector elector = protocol.getHAElector(ResourceTypeMock.Server);
            // 构造 HAObserver start 前的初始事件
            for (int i = 0; i < 3; i++) {
                elector.start(noopRunnable, noopRunnable);
                TimeUnit.SECONDS.sleep(5);
                elector.close();
                TimeUnit.SECONDS.sleep(5);
            }
            try (HAObserverZookeeperImpl observer = (HAObserverZookeeperImpl) protocol.getHAObserver(ResourceTypeMock.Server)) {
                observer.start(eventCounter);
                TimeUnit.SECONDS.sleep(5);
                eventCounter.assertEvents(0, 0);
            }
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    public void testOneLeaderAppearEventObserved() throws Exception {
        EventCounter eventCounter = new EventCounter();
        try (HAProtocol<ResourceTypeMock> protocol = HAProtocolZookeeperImpl.create(connectStringMock, namespaceMock, instanceIdMock)) {
            protocol.start();
            HAElector elector = protocol.getHAElector(ResourceTypeMock.Server);
            // 构造 HAObserver start 前的初始事件
            for (int i = 0; i < 2; i++) {
                elector.start(noopRunnable, noopRunnable);
                TimeUnit.SECONDS.sleep(5);
                elector.close();
                TimeUnit.SECONDS.sleep(5);
            }
            elector.start(noopRunnable, noopRunnable);
            TimeUnit.SECONDS.sleep(5);
            try (HAObserverZookeeperImpl observer = (HAObserverZookeeperImpl) protocol.getHAObserver(ResourceTypeMock.Server)) {
                observer.start(eventCounter);
                TimeUnit.SECONDS.sleep(5);
                eventCounter.assertEvents(1, 0);
            }
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    public void testPairedEventObserved() throws Exception {
        EventCounter eventCounter = new EventCounter();
        try (HAProtocol<ResourceTypeMock> protocol = HAProtocolZookeeperImpl.create(connectStringMock, namespaceMock, instanceIdMock)) {
            protocol.start();
            HAElector elector = protocol.getHAElector(ResourceTypeMock.Server);
            // 构造 HAObserver start 前的初始事件
            for (int i = 0; i < 2; i++) {
                elector.start(noopRunnable, noopRunnable);
                TimeUnit.SECONDS.sleep(5);
                elector.close();
                TimeUnit.SECONDS.sleep(5);
            }
            elector.start(noopRunnable, noopRunnable);
            TimeUnit.SECONDS.sleep(5);
            try (HAObserverZookeeperImpl observer = (HAObserverZookeeperImpl) protocol.getHAObserver(ResourceTypeMock.Server)) {
                observer.start(eventCounter);
                TimeUnit.SECONDS.sleep(5);
                eventCounter.assertEvents(1, 0);
                elector.close();
                TimeUnit.SECONDS.sleep(5);
                eventCounter.assertEvents(1, 1);
            }
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    public void testHAObserverStartFirst() throws Exception {
        try (HAProtocol<ResourceTypeMock> protocol = HAProtocolZookeeperImpl.create(connectStringMock, namespaceMock, instanceIdMock)) {
            protocol.start();
            try (HAObserverZookeeperImpl observer = (HAObserverZookeeperImpl) protocol.getHAObserver(ResourceTypeMock.Server)) {
                observer.start(noopBiConsumer);
                assertLeader(observer, null);
                HAElector elector = protocol.getHAElector(ResourceTypeMock.Server);
                elector.start(noopRunnable, noopRunnable);
                assertLeader(observer, instanceIdMock);
                elector.close();
                assertLeader(observer, null);
                elector.start(noopRunnable, noopRunnable);
                assertLeader(observer, instanceIdMock);
                elector.close();
                assertLeader(observer, null);
            }
            // pathChildrenCache 关闭有延迟
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    public void testHAElectorStartFirst() throws Exception {
        try (HAProtocol<ResourceTypeMock> protocol = HAProtocolZookeeperImpl.create(connectStringMock, namespaceMock, instanceIdMock)) {
            protocol.start();
            HAElector elector = protocol.getHAElector(ResourceTypeMock.Server);
            elector.start(noopRunnable, noopRunnable);
            // 事件监听有延迟需间隔几秒
            TimeUnit.SECONDS.sleep(5);
            try (HAObserverZookeeperImpl observer = (HAObserverZookeeperImpl) protocol.getHAObserver(ResourceTypeMock.Server)) {
                Assert.assertNull(observer.getLastLeader());
                Assert.assertEquals(instanceIdMock, observer.getLeader());
                observer.start(noopBiConsumer);
                assertLeader(observer, instanceIdMock);
                elector.close();
                assertLeader(observer, null);
                elector.start(noopRunnable, noopRunnable);
                assertLeader(observer, instanceIdMock);
                elector.close();
                assertLeader(observer, null);
            }
            // pathChildrenCache 关闭有延迟
            TimeUnit.SECONDS.sleep(5);
        }
    }

    private void assertLeader(HAObserverZookeeperImpl observer, String expectLeader) throws Exception {
        // 事件监听有延迟需间隔几秒
        TimeUnit.SECONDS.sleep(5);
        Assert.assertEquals(expectLeader, observer.getLastLeader());
        Assert.assertEquals(expectLeader, observer.getLeader());
    }

}
