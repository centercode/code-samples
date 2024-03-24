package io.github.centercode.sample.smallfile;


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

/**
 * 为Hadoop作业驱动程序提供通用小文件进行合并功能。
 */
public class SmallFilesConsolidator {

    private static Logger logger = LoggerFactory.getLogger(SmallFilesConsolidator.class);

    // 可配置的HDFS根目录
    private static String MERGED_HDFS_ROOT_DIR = "/tmp/";

    /**
     * 获取Buckets的数量
     *
     * @param totalFiles:总文件数
     * @param numberOfMapSlotsAvailable:
     * @param maxFilesPerBucket:每一个Bucket的最大文件数
     */
    public static int getNumberOfBuckets(int totalFiles, int numberOfMapSlotsAvailable, int maxFilesPerBucket) {
        if (totalFiles <= (maxFilesPerBucket * numberOfMapSlotsAvailable)) {
            return numberOfMapSlotsAvailable;
        } else {
            int numberOfBuckets = totalFiles / maxFilesPerBucket;
            int remainder = totalFiles % maxFilesPerBucket;
            if (remainder == 0) {
                return numberOfBuckets;
            } else {
                return numberOfBuckets + 1;
            }
        }
    }

    /**
     * 为映射器创建Buckets
     */
    public static BucketThread[] createBuckets(int totalFiles, int numberOfMapSlotsAvailable, int maxFilesPerBucket) {
        int numberOfBuckets = getNumberOfBuckets(totalFiles, numberOfMapSlotsAvailable, maxFilesPerBucket);
        BucketThread[] buckets = new BucketThread[numberOfBuckets];
        return buckets;
    }

    /**
     * 填充Bucket
     *
     * @param buckets:所有Bucket列表
     * @param smallFiles:小文件数
     * @param job:Hadoop运行的作业
     * @param maxFilesPerBucket:每一个Bucket的最大文件数
     */
    public static void fillBuckets(BucketThread[] buckets, List<String> smallFiles, Job job, int maxFilesPerBucket)
            throws Exception {

        int numberOfBuckets = buckets.length;
        // 将所有的小文件分区并填充到bucket中
        int combinedSize = smallFiles.size();
        int biosetsPerBucket = combinedSize / numberOfBuckets;
        if (biosetsPerBucket < maxFilesPerBucket) {
            int remainder = combinedSize % numberOfBuckets;
            if (remainder != 0) {
                biosetsPerBucket++;
            }
        }

        String parentDir = getParentDir();
        // 使用Bucket的序号定义Bucket的Id(范围是从0到numberOfBuckets-1)
        int id = 0;
        int index = 0;
        boolean done = false;
        while ((!done) & (id < numberOfBuckets)) {
            // 创建一个Bucket对象
            buckets[id] = new BucketThread(parentDir, id, job.getConfiguration());
            // 使用小文件填充Bucket
            for (int b = 0; b < biosetsPerBucket; b++) {
                buckets[id].add(smallFiles.get(index));
                index++;
                if (index == combinedSize) {
                    done = true;
                    break;
                }
            }
            id++;
        }
    }

    /**
     * 对于每一个Bucket启动一个线程，并合并小文件
     */
    public static void mergeEachBucket(BucketThread[] buckets, Job job) throws Exception {
        if (buckets == null) {
            return;
        }

        int numberOfBuckets = buckets.length;
        if (numberOfBuckets < 1) {
            return;
        }

        for (int ID = 0; ID < numberOfBuckets; ID++) {
            if (buckets[ID] != null) {
                buckets[ID].start();
            }
        }

        // 等待所有线程完成
        for (int ID = 0; ID < numberOfBuckets; ID++) {
            if (buckets[ID] != null) {
                buckets[ID].join();
            }
        }

        for (int ID = 0; ID < numberOfBuckets; ID++) {
            if (buckets[ID] != null) {
                Path biosetPath = buckets[ID].getTargetDir();
                addInputPathWithoutCheck(job, biosetPath);
            }
        }
    }

    private static void addInputPathWithoutCheck(Job job, Path path) {
        try {
            FileInputFormat.addInputPath(job, path);
            logger.info("added path: " + path);
        } catch (Exception e) {
            logger.error("could not add path: " + path, e);
        }
    }

    private static String getParentDir() {
        String guid = UUID.randomUUID().toString();
        return MERGED_HDFS_ROOT_DIR + guid + "/";
    }

}