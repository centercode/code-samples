package io.centercode.sample;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.RegionLocator;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.tool.LoadIncrementalHFiles;

/**
 * 通过BulkLoad快速将海量数据导入到Hbase[Hadoop篇]
 * https://www.iteblog.com/archives/1889.html#MapReduceHFile
 */
public class LoadToHBase {
    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.addHbaseResources(HBaseConfiguration.create());
        LoadIncrementalHFiles loader = new LoadIncrementalHFiles(conf);
        try (Connection conn = ConnectionFactory.createConnection(conf)) {
            Admin admin = conn.getAdmin();
            TableName tableName = TableName.valueOf("blog_info");
            Table table = conn.getTable(tableName);
            RegionLocator locator = conn.getRegionLocator(tableName);
            loader.doBulkLoad(new Path(BulkLoadDriver.DST_PATH), admin, table, locator);
        }
    }
}