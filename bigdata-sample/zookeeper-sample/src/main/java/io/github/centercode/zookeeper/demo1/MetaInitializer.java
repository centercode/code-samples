package io.github.centercode.zookeeper.demo1;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class MetaInitializer {

    ZooKeeper zk;

    public void init() {
        createParent("/workers", new byte[0]);
        createParent("/assign", new byte[0]);
        createParent("/tasks", new byte[0]);
        createParent("/status", new byte[0]);
    }

    void createParent(String path, byte[] data) {
        zk.create(path,
                data,
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT,
                null,
                data);
    }

    AsyncCallback.StringCallback createParentCB = (rc, path, ctx, name) -> {
        KeeperException.Code code = KeeperException.Code.get(rc);
        switch (code) {
            case CONNECTIONLOSS:
                createParent(path, (byte[]) ctx);
                break;
            case OK:
                System.out.println("Parent created");
                break;
            case NODEEXISTS:
                System.out.println("Parent already registered:" + path);
                break;
            default:
                System.err.println("Error:" + KeeperException.create(code, path));
        }
    };
}
