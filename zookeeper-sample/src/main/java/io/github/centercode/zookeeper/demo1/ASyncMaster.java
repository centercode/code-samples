package io.github.centercode.zookeeper.demo1;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class ASyncMaster implements Watcher {

    private static boolean isLeader = false;

    private String serverId = Integer.toString(new Random().nextInt());

    private ZooKeeper zk;

    private String connectString;

    public ASyncMaster(String connectString) {
        this.connectString = connectString;
    }


    void runForMaster() {
        zk.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, masterCreateCB, null);
    }

    AsyncCallback.StringCallback masterCreateCB = (rc, path, ctx, name) -> {
        switch (KeeperException.Code.get(rc)) {
            case CONNECTIONLOSS:
                checkMaster();
                return;
            case OK:
                isLeader = true;
                break;
            default:
                isLeader = false;
        }
        System.out.println("I'm " + (isLeader ? "" : " not ") + "the leader");
    };

    void checkMaster() {
        zk.getData("/master", false, masterCheckCB, null);
    }

    AsyncCallback.DataCallback masterCheckCB = (rc, path, ctx, data, stat) -> {
        switch (KeeperException.Code.get(rc)) {
            case CONNECTIONLOSS:
                checkMaster();
                return;
            case NONODE:
                runForMaster();
                return;
        }
    };

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ASyncMaster master = new ASyncMaster(args[0]);
        master.start();
        master.runForMaster();
        if (isLeader) {
            System.out.println("I'm the leader");
            TimeUnit.SECONDS.sleep(60L);
        } else {
            System.out.println("Someone else is the leader");
        }
        master.stop();
    }

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
