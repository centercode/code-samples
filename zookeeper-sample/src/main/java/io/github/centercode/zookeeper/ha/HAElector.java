package io.github.centercode.zookeeper.ha;

import java.io.Closeable;
import java.io.IOException;

/**
 * 基于 {@link HAProtocol} 参与 Leader 选举
 */
public interface HAElector extends Closeable {

    /**
     * 启动并竞争 Leader 权限
     *
     * @param takeLeadershipAction  当获取到 Leader 权限时进行的操作
     * @param leaveLeadershipAction 当丢失 Leader 权限时进行的操作
     */
    void start(Runnable takeLeadershipAction, Runnable leaveLeadershipAction) throws Exception;

    /**
     * 停止竞争 Leader 权限
     */
    @Override
    void close();

    /**
     * 判断当前 {@link HAElector} 是否是 leader
     */
    boolean isLeader() throws IOException;
}
