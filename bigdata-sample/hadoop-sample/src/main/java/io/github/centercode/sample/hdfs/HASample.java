package io.github.centercode.sample.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.function.Consumer;

/**
 * 高可用连接方式
 */
public class HASample {

    public void connect(
            List<String> hdfsUri,
            String hdfsUser,
            Consumer<FileSystem> consumer
    ) throws IOException, InterruptedException {
        Configuration conf = new Configuration(false);
        conf.set("fs.defaultFS", "hdfs://cluster");
        conf.set("dfs.nameservices", "cluster");
        conf.set("dfs.ha.namenodes.cluster", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.cluster.nn1", hdfsUri.get(0));
        conf.set("dfs.namenode.rpc-address.cluster.nn2", hdfsUri.get(1));
        conf.set("dfs.client.failover.proxy.provider.cluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        try (FileSystem fs = FileSystem.get(URI.create("hdfs://cluster"), conf, hdfsUser)) {
            consumer.accept(fs);
        }
    }
}
