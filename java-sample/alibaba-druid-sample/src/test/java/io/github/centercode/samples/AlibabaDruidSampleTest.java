package io.github.centercode.samples;

import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.parser.ParserException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AlibabaDruidSampleTest {

    /**
     * issue-5494
     */
    @Test(expected = ParserException.class)
    public void testIssue5494() throws IOException {
        String sql = IOUtils.resourceToString("/issue-5494.sql", StandardCharsets.UTF_8);
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        parser.parseSelect();
    }

}