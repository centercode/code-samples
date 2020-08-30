package io.github.centercode.zookeeper.demo1;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class Client implements Watcher {

    private ZooKeeper zk;

    String queueCommand(String taskName, String command) throws Exception {
        while (true) {
            try {
                return zk.create("/tasks/task-",
                        command.getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT_SEQUENTIAL);
            } catch (KeeperException.NodeExistsException e) {
                throw new Exception(taskName + "already appear to be running");
            } catch (KeeperException.ConnectionLossException e) {

            }
        }
    }


    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }
}
