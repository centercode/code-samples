package io.github.centercode.sample.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class MergeFile extends Configured {

    /**
     * HDFS到HDFS的合并
     */
    public void copyMerge(String srcDir, String dstFile) throws IOException {
        Configuration conf = getConf();
        Path src = new Path(srcDir);
        Path dst = new Path(dstFile);
        FileUtil.copyMerge(
                src.getFileSystem(conf), src,
                dst.getFileSystem(conf), dst,
                false, conf, null);
    }


}
