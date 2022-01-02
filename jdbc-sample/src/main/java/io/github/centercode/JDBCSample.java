package io.github.centercode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class JDBCSample {

    private final String url;

    private final String user;

    private final String password;

    public JDBCSample(String url, String user, String password) {
        this.url = Objects.requireNonNull(url);
        this.user = user;
        this.password = password;
    }

    public static void main(String[] args) throws SQLException {
        String url = System.getProperty("url");
        String user = System.getProperty("user");
        String password = System.getProperty("password");
        JDBCSample sample = new JDBCSample(url, user, password);
        String sql = System.getProperty("sql");
        sample.executeQuery(sql);
    }

    public void executeQuery(String sql) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    consume(rs);
                }
            }
        }
        System.out.println("Execute query finished.");
    }

    private void consume(ResultSet rs) throws SQLException {
        int count = 0;
        while (rs.next()) {
            count++;
        }
        System.out.println("ResultSet count: " + count);
    }
}
