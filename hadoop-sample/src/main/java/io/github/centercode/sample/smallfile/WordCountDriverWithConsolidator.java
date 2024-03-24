package io.github.centercode.sample.smallfile;


import io.github.centercode.sample.wordcount.WordCountMapper;
import io.github.centercode.sample.wordcount.WordCountReducer;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 使用小文件合并的单词计数驱动程序
 */
public class WordCountDriverWithConsolidator extends Configured implements Tool {

    private static final Logger logger = LoggerFactory.getLogger(WordCountDriverWithConsolidator.class);
    private static int NUMBER_OF_MAP_SLOTS_AVAILABLE = 8;
    // 每一个bucket的最大文件数
    private static int MAX_FILES_PER_BUCKET = 5;

    private String inputDir = null;
    private String outputDir = null;
    private Job job = null;

    public WordCountDriverWithConsolidator(String inputDir, String outputDir) {
        this.inputDir = inputDir;
        this.outputDir = outputDir;
    }

    /**
     * 启动Job
     */
    public int run(String[] args) throws Exception {
        this.job = new Job(getConf(), "WordCountDriverWithConsolidator");
        job.getConfiguration().setInt("word.count.ignored.length", 3);

        // 将所有jar文件添加到HDFS的分布式缓存中
        HadoopUtil.addJarsToDistributedCache(job, "/lib/");

        // 获取HDFS文件系统
        FileSystem fs = FileSystem.get(job.getConfiguration());
        List<String> smallFiles = HadoopUtil.listDirectoryAsListOfString(inputDir, fs);
        int size = smallFiles.size();
        if (size <= NUMBER_OF_MAP_SLOTS_AVAILABLE) {
            for (String file : smallFiles) {
                logger.info("file=" + file);
                addInputPath(fs, job, file);
            }
        } else {
            // 创建文件Bucket,每一个Bucket将会添加小文件
            BucketThread[] buckets = SmallFilesConsolidator.createBuckets(size, NUMBER_OF_MAP_SLOTS_AVAILABLE,
                    MAX_FILES_PER_BUCKET);
            SmallFilesConsolidator.fillBuckets(buckets, smallFiles, job, MAX_FILES_PER_BUCKET);
            SmallFilesConsolidator.mergeEachBucket(buckets, job);
        }

        // 输出路径
        FileOutputFormat.setOutputPath(job, new Path(outputDir));

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(WordCountReducer.class);
        job.setReducerClass(WordCountReducer.class);

        boolean status = job.waitForCompletion(true);
        logger.info("run(): status=" + status);
        return status ? 0 : 1;
    }

    /**
     * 添加输入路径
     */
    private void addInputPath(FileSystem fs, Job job, String pathAsString) {
        try {
            Path path = new Path(pathAsString);
            if (fs.exists(path)) {
                FileInputFormat.addInputPath(job, path);
            } else {
                logger.info("addInputPath(): path does not exist. ignored: " + pathAsString);
            }
        } catch (Exception e) {
            logger.error("addInputPath(): could not add path: " + pathAsString, e);
        }
    }

    /**
     * 提交map/reduce作业
     */
    public static int submitJob(String inputDir, String outputDir) throws Exception {
        WordCountDriverWithConsolidator driver = new WordCountDriverWithConsolidator(inputDir, outputDir);
        int status = ToolRunner.run(driver, null);
        logger.info("submitJob(): status=" + status);
        return status;
    }

    /**
     * Wordcount的map/reduce程序的主驱动程序。调用此方法提交map/reduce作业。
     *
     * @throws Exception:作业跟踪器通信问题时抛出异常。
     */
    public static void main(String[] args) throws Exception {
        // 确定有两个参数
        if (args.length != 2) {
            logger.warn("2 arguments. <input-dir>, <output-dir>");
            throw new IllegalArgumentException("2 arguments. <input-dir>, <output-dir>");
        }

        logger.info("inputDir=" + args[0]);
        logger.info("outputDir=" + args[1]);
        long startTime = System.currentTimeMillis();
        int returnStatus = submitJob(args[0], args[1]);
        long elapsedTime = System.currentTimeMillis() - startTime;
        logger.info("returnStatus=" + returnStatus);
        logger.info("Finished in milliseconds: " + elapsedTime);
        System.exit(returnStatus);
    }
}
