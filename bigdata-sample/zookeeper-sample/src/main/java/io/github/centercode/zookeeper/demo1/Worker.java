package io.github.centercode.zookeeper.demo1;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class Worker implements Watcher {

    String serverId = "";

    ZooKeeper zk;

    String connectString;

    String status;

    public static void main(String[] args) throws IOException {
        Worker worker = new Worker();
        worker.start();
        worker.register();
    }

    AsyncCallback.StatCallback statusUpdateCallback = (rc, path, ctx, name) -> {
        KeeperException.Code code = KeeperException.Code.get(rc);
        switch (code) {
            case CONNECTIONLOSS:
                updateStatus((String) ctx);
                return;
        }
    };

    public void setStatus(String status) {
        this.status = status; //为了重试
        updateStatus(status);
    }

    /**
     * 竟态条件检查
     */
    synchronized private void updateStatus(String status) {
        if (status.equals(this.status)) {
            zk.setData("/workers/" + serverId,
                    status.getBytes(),
                    -1,
                    statusUpdateCallback,
                    status);
        }
    }

    AsyncCallback.StringCallback registerCallback = (rc, path, ctx, name) -> {
        KeeperException.Code code = KeeperException.Code.get(rc);
        switch (code) {
            case CONNECTIONLOSS:
                register();
                return;
            case OK:
                System.out.println("Register success:" + serverId);
                return;
            case NODEEXISTS:
                System.out.println("Already registered: " + path);
                return;
            default:
                System.err.println("ERROR:" + KeeperException.create(code, path));
        }
    };

    void register() {
        zk.create("/workers/" + serverId,
                "Idle".getBytes(), //从节点状态信息
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                registerCallback,
                null);
    }

//    AsyncCallback.ChildrenCallback waitCB = (rc, path, ctx, children) -> {
//        KeeperException.Code code = KeeperException.Code.get(rc);
//        switch (code) {
//            case CONNECTIONLOSS:
//                waitAssign();
//                return;
//            case OK:
//                //process
//                return;
//            default:
//                System.err.println("ERROR");
//        }
//    };
//
//    void waitAssign() {
//        zk.getChildren("/assigns",
//                this,
//                waitCB,
//                null);
//    }

    void start() throws IOException {
        zk = new ZooKeeper(connectString, 15000, this);
    }

    private void stop() throws InterruptedException {
        zk.close();
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

}
