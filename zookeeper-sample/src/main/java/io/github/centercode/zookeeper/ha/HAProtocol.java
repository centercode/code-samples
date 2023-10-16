package io.github.centercode.zookeeper.ha;

import java.io.Closeable;

/**
 * 一个高可用协议接口，主要用来获取 {@link HAElector} 和 {@link HAObserver} 实例
 */
public interface HAProtocol<T extends ResourceType> extends Closeable {

    /**
     * 启动服务
     */
    void start() throws Exception;

    /**
     * 停止服务
     */
    @Override
    void close();

    /**
     * 根据高可用资源的种类获取 {@link HAElector} 的实例
     *
     * @param resourceType 高可用资源的种类
     */
    HAElector getHAElector(T resourceType) throws Exception;

    /**
     * 根据高可用资源的种类获取 {@link HAObserver} 的实例
     *
     * @param resourceType 高可用资源的种类
     */
    HAObserver getHAObserver(T resourceType) throws Exception;
}
