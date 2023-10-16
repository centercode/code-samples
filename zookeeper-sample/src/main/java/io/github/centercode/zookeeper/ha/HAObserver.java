package io.github.centercode.zookeeper.ha;

import java.io.Closeable;
import java.util.function.BiConsumer;

/**
 * 基于 {@link HAProtocol} 获取 Leader 状态，不参与Leader竞争
 */
public interface HAObserver extends Closeable {

    /**
     * 启动并监听 Leader 状态变化
     *
     * @param leaderChangeListener 当 Leader 发生变更的时候做的操作.
     *                             BiConsumer.accept() 参数为 oldLeader, newLeader
     */
    void start(BiConsumer<String, String> leaderChangeListener) throws Exception;

    /**
     * 停止监听 Leader 状态变化
     */
    @Override
    void close();

    /**
     * 获取高可用资源的 Leader，当不存在Leader时可以返回 null
     */
    String getLeader() throws Exception;
}
