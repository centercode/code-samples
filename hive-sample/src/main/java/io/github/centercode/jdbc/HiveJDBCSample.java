package io.github.centercode.jdbc;

import org.apache.hive.jdbc.HiveConnection;
import org.apache.hive.jdbc.HiveStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HiveJDBCSample {

    private static final Logger logger = LoggerFactory.getLogger(HiveJDBCSample.class);

    String url;
    String username;
    String password;

    public HiveJDBCSample(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:hive2://localhost:21050/default;auth=noSasl";
        HiveJDBCSample sample = new HiveJDBCSample(url, null, null);
        String sql = "select sleep(5000)";
        sample.runOnce(sql);
    }

    void runOnce(String sql) throws SQLException {
        try (HiveConnection connection = (HiveConnection) DriverManager.getConnection(url, username, password)) {
            try (HiveStatement stmt = (HiveStatement) connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    printQueryLog(stmt);
                    printFirstColumnResult(rs);
                }
            }
        }
        logger.info("Done");
    }

    private void printQueryLog(HiveStatement stmt) throws SQLException {
        do {
            logger.info("Hive query log:" + stmt.getQueryLog());
        } while (stmt.hasMoreLogs());
    }

    private void printFirstColumnResult(ResultSet rs) throws SQLException {
        logger.info("Result:");
        while (rs.next()) {
            logger.info(rs.getString(1));
        }
    }
}
