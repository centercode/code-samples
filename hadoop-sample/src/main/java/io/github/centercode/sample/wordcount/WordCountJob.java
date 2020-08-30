package io.github.centercode.sample.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

/**
 * https://www.iteblog.com/archives/2147.html
 */
public class WordCountJob extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        WordCountJob wordCountJob = new WordCountJob();
        int code = wordCountJob.run(args);
        System.exit(code);
    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        Job job = Job.getInstance(conf, this.getClass().getSimpleName());
        job.setJarByClass(WordCountJob.class);

        //mapper
        job.setMapperClass(WordCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        job.setCombinerClass(WordCountCombiner.class);
        job.setSortComparatorClass(Text.Comparator.class);

        //reducer
        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setNumReduceTasks(Integer.valueOf(args[2]));

        return (job.waitForCompletion(false) ? 0 : 1);
    }
}