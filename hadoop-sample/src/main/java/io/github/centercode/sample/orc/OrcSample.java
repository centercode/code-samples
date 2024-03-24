package io.github.centercode.sample.orc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.orc.OrcFile;
import org.apache.orc.Reader;
import org.apache.orc.RecordReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OrcSample extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        OrcSample orcSample = new OrcSample();
        ToolRunner.run(orcSample, args);
    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        FileSystem fs = FileSystem.get(conf);
        System.out.println(fs.getClass());

        List<String> inputs = Files.readAllLines(Paths.get(args[0]));
        long count = 0;
        for (String input : inputs) {
            long size = readWrite(new Path(input), conf);
            count += size;
        }
        System.out.println("Count:" + count);

        return 0;
    }

    long readWrite(Path file, Configuration conf) throws IOException {
        long count = 0;
        Reader reader = OrcFile.createReader(file, OrcFile.readerOptions(conf));
        try (RecordReader rows = reader.rows()) {
            VectorizedRowBatch batch = reader.getSchema().createRowBatch();
            try {
                while (rows.nextBatch(batch)) {
                    count += batch.size;
                    LongColumnVector idVector = (LongColumnVector) batch.cols[0];
                    BytesColumnVector sqlVector = (BytesColumnVector) batch.cols[4];
                    for (int r = 0; r < batch.size; r++) {
                        long id = idVector.vector[r];
                        String sql = sqlVector.toString(r);
//                    System.out.println("Id " + id); // + ":" + sql.substring(0, Math.min(sql.length(), 10))
                        Files.write(Paths.get("/tmp/sql_orc/output/" + id + ".sql"), sql.getBytes(StandardCharsets.UTF_8));
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return count;
    }
}
