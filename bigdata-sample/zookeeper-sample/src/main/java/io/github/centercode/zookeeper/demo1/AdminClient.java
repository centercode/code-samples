package io.github.centercode.zookeeper.demo1;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class AdminClient {

    private ZooKeeper zk;

    void listState() throws KeeperException, InterruptedException {
        try {
            byte[] masterData = zk.getData("/master", false, new Stat());
            System.out.println("Master:" + new String(masterData));

        } catch (KeeperException.NoNodeException e) {
            System.err.println("no Master");
        }

        System.out.println("workers:");
        List<String> workers = zk.getChildren("/workers", false);
        for (String worker : workers) {
            byte[] workerData = zk.getData("/workers/" + worker, false, null);
            System.out.println("worker status:" + new String(workerData));
        }

        System.out.println("Tasks:");

        List<String> tasks = zk.getChildren("/assign", false);
        for (String task : tasks) {
            System.out.println("task:" + task);
        }
    }
}
