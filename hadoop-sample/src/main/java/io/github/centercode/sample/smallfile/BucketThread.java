package io.github.centercode.sample.smallfile;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类提供了将小于块大小的文件合并为一个大于块大小的文件，这样将提交较少的map()作业，提高map的运行效率。
 */
public class BucketThread implements Runnable {

    private static Logger theLogger = LoggerFactory.getLogger(BucketThread.class);
    private static final Path NULL_PATH = new Path("/tmp/null");

    private Thread runner = null;
    private List<Path> bucket = null;
    private Configuration conf = null;
    private FileSystem fs = null;
    private String parentDir = null;

    private String targetDir = null;
    private String targetFile = null;

    /**
     * 创建一个新的Bucket线程对象
     *
     * @param parentDir:父目录
     * @param id:           每一个Bucket都有一个唯一的ID
     */
    public BucketThread(String parentDir, int id, Configuration conf) throws IOException {
        this.parentDir = parentDir;
        // 存储目标目录
        this.targetDir = parentDir + id;
        // 存储目标文件
        this.targetFile = targetDir + "/" + id;
        this.conf = conf;
        this.runner = new Thread(this);
        this.fs = FileSystem.get(this.conf);
        this.bucket = new ArrayList<Path>();
    }

    /**
     * 启动线程
     */
    public void start() {
        runner.start();
    }

    /**
     * 连接并等待其他线程
     */
    public void join() throws InterruptedException {
        runner.join();
    }

    /**
     * 线程的核心执行
     */
    public void run() {
        try {
            copyMerge();
        } catch (Exception e) {
            theLogger.error("run(): copyMerge() failed.", e);
        }
    }

    /**
     * @param path :添加一个文件到Bucket中
     */
    public void add(String path) {
        if (path == null) {
            return;
        }

        Path hdfsPath = new Path(path);
        if (pathExists(hdfsPath)) {
            bucket.add(hdfsPath);
        }
    }

    public List<Path> getBucket() {
        return bucket;
    }

    public int size() {
        return bucket.size();
    }

    public Path getTargetDir() {
        if (size() == 0) {
            // 没有文件的空目录
            return NULL_PATH;
        } else if (size() == 1) {
            return bucket.get(0);
        } else {
            // bucket有两个或更多的文件，并且已经被合并
            return new Path(targetDir);
        }
    }

    /**
     * 将多个目录中的所有文件复制到一个输出文件(合并)。
     * <p>
     * 将bucket中的所有路径合并，并返回一个新的目录(targetDir)，该目录包含合并的路径。
     */
    public void copyMerge() throws IOException {
        // 如果bucket中只有一个路径/dir，则不需要合并它
        if (size() < 2) {
            return;
        }

        Path hdfsTargetFile = new Path(targetFile);
        OutputStream out = fs.create(hdfsTargetFile);
        try {
            for (int i = 0; i < bucket.size(); i++) {
                FileStatus contents[] = fs.listStatus(bucket.get(i));
                for (int k = 0; k < contents.length; k++) {
                    if (!contents[k].isDir()) {
                        InputStream in = fs.open(contents[k].getPath());
                        try {
                            IOUtils.copyBytes(in, out, conf, false);
                        } finally {
                            org.apache.commons.io.IOUtils.closeQuietly(in);
                        }
                    }
                }

            }
        } finally {
            org.apache.commons.io.IOUtils.closeQuietly(out);
        }

    }

    public String getParentDir() {
        return parentDir;
    }

    /**
     * HDFS目录存在，则返回true,否则返回false
     */
    public boolean pathExists(Path path) {
        if (path == null) {
            return false;
        }

        try {
            return fs.exists(path);
        } catch (Exception e) {
            return false;
        }
    }

    public String toString() {
        return bucket.toString();
    }

}