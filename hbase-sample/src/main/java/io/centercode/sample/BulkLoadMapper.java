package io.centercode.sample;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class BulkLoadMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put> {
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] items = line.split("\t");

        ImmutableBytesWritable rowKey = new ImmutableBytesWritable(items[0].getBytes());
        Put put = new Put(Bytes.toBytes(items[0]));   //ROWKEY
        put.addColumn("f1".getBytes(), "url".getBytes(), items[1].getBytes());
        put.addColumn("f1".getBytes(), "name".getBytes(), items[2].getBytes());

        context.write(rowKey, put);
    }
}
