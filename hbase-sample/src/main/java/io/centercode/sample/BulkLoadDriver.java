package io.centercode.sample;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RegionLocator;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class BulkLoadDriver {
    public static final String SRC_PATH = "hdfs://iteblog:9000/user/iteblog/input";
    public static final String DST_PATH = "hdfs://iteblog:9000/user/iteblog/output";

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = HBaseConfiguration.create();
        Job job = Job.getInstance(conf);
        job.setJarByClass(BulkLoadDriver.class);
        job.setMapperClass(BulkLoadMapper.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(Put.class);
        job.setOutputFormatClass(HFileOutputFormat2.class);
        try (Connection conn = ConnectionFactory.createConnection(conf)) {
            TableName tableName = TableName.valueOf("blog_info");
            Table table = conn.getTable(tableName);
            RegionLocator locator = conn.getRegionLocator(tableName);
            HFileOutputFormat2.configureIncrementalLoad(job, table, locator);
        }
        FileInputFormat.addInputPath(job, new Path(SRC_PATH));
        FileOutputFormat.setOutputPath(job, new Path(DST_PATH));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}