package io.github.centercode.zookeeper.ha.impl;

import com.google.common.base.Preconditions;
import io.github.centercode.zookeeper.ha.HAElector;
import io.github.centercode.zookeeper.ha.HAObserver;
import io.github.centercode.zookeeper.ha.HAProtocol;
import io.github.centercode.zookeeper.ha.ResourceType;
import org.apache.commons.io.IOUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryForever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * A ZooKeeper based HAProtocol implementation.
 */
public class HAProtocolZookeeperImpl<T extends ResourceType> implements HAProtocol<T> {

    private final static Logger LOG = LoggerFactory.getLogger(HAProtocolZookeeperImpl.class);

    /**
     * ZooKeeper连接地址
     */
    private final java.lang.String connectString;

    /**
     * ZooKeeper namespace
     */
    private final java.lang.String namespace;

    /**
     * 高可用资源的信息，存储到ZooKeeper对应节点的数据中
     */
    private final java.lang.String resource;

    /**
     * 服务是否已经启动
     */
    boolean started;
    /**
     * curatorClient实例
     */
    CuratorFramework curatorClient;

    HashMap<ResourceType, HAElector> electorInstanceMap = new HashMap<>();

    HashMap<ResourceType, HAObserver> observerInstanceMap = new HashMap<>();

    /**
     * 创建 HAProtocol 的实例
     *
     * @param connectString ZooKeeper连接地址
     * @param namespace     ZooKeeper namespace
     * @param resource      高可用资源的信息
     * @return HAProtocol的实例
     */
    public static <T extends ResourceType> HAProtocol<T> create(
        java.lang.String connectString,
        java.lang.String namespace,
        java.lang.String resource) {
        return new HAProtocolZookeeperImpl<>(connectString, namespace, resource);
    }

    /**
     * HAProtocol的简单构造函数
     *
     * @param connectString ZooKeeper连接地址
     * @param namespace     ZooKeeper namespace
     * @param resource      高可用资源的信息
     */
    HAProtocolZookeeperImpl(
        java.lang.String connectString,
        java.lang.String namespace,
        java.lang.String resource
    ) {
        this(connectString, namespace, resource, new RetryForever(5_000), 10_000, 50_000);
    }

    /**
     * HAProtocol的完整构造函数
     *
     * @param connectString       ZooKeeper连接地址
     * @param namespace           ZooKeeper namespace
     * @param resource            高可用资源的信息
     * @param retryPolicy         ZooKeeper 重试策略
     * @param connectionTimeoutMs ZooKeeper 连接超时时间
     * @param sessionTimeoutMs    ZooKeeper Session超时时间
     */
    HAProtocolZookeeperImpl(
        java.lang.String connectString,
        java.lang.String namespace,
        java.lang.String resource,
        RetryPolicy retryPolicy,
        int connectionTimeoutMs,
        int sessionTimeoutMs
    ) {
        this.connectString = connectString;
        this.namespace = namespace;
        this.resource = resource;
        this.started = false;
        this.curatorClient = CuratorFrameworkFactory.builder()
            .connectString(connectString)
            .namespace(namespace)
            .retryPolicy(retryPolicy)
            .connectionTimeoutMs(connectionTimeoutMs)
            .sessionTimeoutMs(sessionTimeoutMs)
            .build();
    }

    @Override
    public synchronized void start() {
        Preconditions.checkState(!started, HAProtocol.class.getSimpleName() + " already started.");
        LOG.trace("curator client starting.");
        this.curatorClient.start();
        LOG.info("curator client started.[connectString='{}', namespace='{}']", connectString, namespace);
        this.started = true;
    }

    @Override
    public synchronized void close() {
        if (started) {
            LOG.info("close {}.", HAProtocol.class.getSimpleName());
            for (HAElector elector : electorInstanceMap.values()) {
                if (null != elector) {
                    IOUtils.closeQuietly(elector);
                }
            }
            for (HAObserver observer : observerInstanceMap.values()) {
                if (null != observer) {
                    IOUtils.closeQuietly(observer);
                }
            }
            electorInstanceMap.clear();
            observerInstanceMap.clear();
            IOUtils.closeQuietly(
                this.curatorClient,
                e -> LOG.error("error when close curatorClient.", e)
            );
            this.started = false;
        }
    }

    /**
     * 创建并维护{@link HAElector}的单例
     */
    @Override
    public synchronized HAElector getHAElector(T resourceType) {
        java.lang.String lockPath = getLockPath(resourceType);
        return electorInstanceMap.computeIfAbsent(resourceType, r -> {
            LOG.info("create {}.[resourceType='{}', lockPath='{}']", HAElector.class.getSimpleName(), resourceType, lockPath);
            return new HAElectorZookeeperImpl(curatorClient, lockPath, resource);
        });
    }

    /**
     * 创建并维护{@link HAObserver}的单例
     */
    @Override
    public synchronized HAObserver getHAObserver(T resourceType) {
        java.lang.String lockPath = getLockPath(resourceType);
        return observerInstanceMap.computeIfAbsent(resourceType, r -> {
            LOG.info("create {}.[resourceType='{}', lockPath='{}']", HAObserver.class.getSimpleName(), resourceType, lockPath);
            return new HAObserverZookeeperImpl(curatorClient, lockPath);
        });
    }

    /**
     * the lockPath of {@link io.github.centercode.zookeeper.leader.LeaderLock}
     */
    private java.lang.String getLockPath(ResourceType resourceType) {
        return "/" + resourceType.name().toLowerCase() + "/leader";
    }
}
