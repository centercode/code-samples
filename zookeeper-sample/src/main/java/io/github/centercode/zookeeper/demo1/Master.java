package io.github.centercode.zookeeper.demo1;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

public class Master implements Watcher {

    String serverId = Integer.toString(new Random().nextInt());

    boolean isLeader = false;

    ZooKeeper zk;

    String connectString;

    public Master(String connectString) {
        this.connectString = connectString;
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        Master master = new Master(args[0]);
        master.start();
        master.runForMaster();
        if (master.isLeader) {
            System.out.println("I'm the leader");
            TimeUnit.SECONDS.sleep(60L);
        } else {
            System.out.println("Someone else is the leader");
        }
        master.stop();
    }

    boolean checkMaster() throws InterruptedException {
        while (true) {
            Stat stat = new Stat();
            try {
                byte[] data = zk.getData("/master", false, stat);
                isLeader = new String(data).equals(serverId);
                return true;
            } catch (KeeperException.NoNodeException e) {
                //no master, so try create again
                return false;
            } catch (KeeperException ignored) {
            }
        }
    }

    void runForMaster() throws KeeperException, InterruptedException {
        while (true) {
            try {
                zk.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (KeeperException.NodeExistsException e) {
                isLeader = false;
                break;
            } catch (KeeperException.ConnectionLossException ignored) {
            }
            if (checkMaster()) {
                break;
            }
        }
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
