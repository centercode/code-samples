package io.github.centercode.sample.hdfs;

import org.apache.hadoop.fs.FsStatus;
import org.apache.hadoop.fs.Path;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HASampleTest {

    private static Logger logger = LoggerFactory.getLogger(HASampleTest.class);

    @Test
    public void connect() throws IOException, InterruptedException {
        List<String> hdfsUri = Arrays.asList("hdfs://192.168.0.1:8020", "hdfs://192.168.0.2:8020");
        HASample sample = new HASample();
        sample.connect(hdfsUri, "hdfs", fs -> {
            try {
                FsStatus status = fs.getStatus(new Path("/user/hdfs/"));
                logger.info("Status.capacity: {}", status.getCapacity());
            } catch (IOException e) {
                logger.error("ERROR", e);
            }
        });
    }
}
