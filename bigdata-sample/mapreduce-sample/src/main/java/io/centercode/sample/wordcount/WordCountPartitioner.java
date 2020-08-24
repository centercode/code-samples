package io.centercode.sample.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class WordCountPartitioner extends HashPartitioner<Text, LongWritable> {
}
