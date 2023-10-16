package io.github.centercode.zookeeper.ha.impl;

import io.github.centercode.zookeeper.ha.HAElector;
import io.github.centercode.zookeeper.ha.HAProtocol;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@Ignore
public class HAProtocolZookeeperImplTest {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    String connectStringMock = "localhost:2181";

    String instanceIdMock = "host01:8888";

    String namespaceMock = "";

    Runnable noopRunnable = () -> {
    };

    @Test
    public void testCatalogHA() throws Exception {
        LOG.info("starting.");
        try (HAProtocol<ResourceTypeMock> haProtocol = HAProtocolZookeeperImpl.create(connectStringMock, namespaceMock, instanceIdMock)) {
            haProtocol.start();
            HAElector elector = haProtocol.getHAElector(ResourceTypeMock.Server);
            elector.start(noopRunnable, noopRunnable);
            TimeUnit.SECONDS.sleep(5);
//            LockSupport.park(LOG);
        }
        LOG.info("done");
    }
}
