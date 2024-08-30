package io.github.centercode.starrocks.jdbc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;

public class DataTypeSample {

    private final String url;

    private final String user;

    private final String password;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public DataTypeSample(String url, String user, String password) {
        this.url = Objects.requireNonNull(url);
        this.user = user;
        this.password = password;
    }

    public static void main(String[] args) throws SQLException, IOException, URISyntaxException {
        String url = System.getProperty("url");
        String user = System.getProperty("user");
        String password = System.getProperty("password");
        DataTypeSample sample = new DataTypeSample(url, user, password);
        String sql = sample.loadResourceFile("data_types_sample.sql");
        sample.listTypesMeta(sql);
    }

    String loadResourceFile(String resourceFile) throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource(resourceFile).toURI());
        return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    }

    void listTypesMeta(String sql) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()
        ) {
            ResultSetMetaData meta = rs.getMetaData();
            System.out.println("ColumnName\tJdbcType\tColumnTypeName\tColumnDisplaySize");
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                String columnName = meta.getColumnName(i);
                // Reference:
                //   https://github.com/StarRocks/starrocks/blob/3.1.6/fe/fe-core/src/main/java/com/starrocks/catalog/PrimitiveType.java#L521
                JDBCType jdbcType = JDBCType.valueOf(meta.getColumnType(i));
                String columnTypeName = meta.getColumnTypeName(i);
                int displaySize = meta.getColumnDisplaySize(i);
                switch (jdbcType) {
                    case DECIMAL:
                        int precision = meta.getPrecision(i);
                        int scale = meta.getScale(i);
                        System.out.println(columnName + "\t" + jdbcType + "\t" + columnTypeName + "(" + precision + "," + scale + ")" + "\t" + displaySize);
                        break;
                    case CHAR:
                    case VARCHAR:
                    case BINARY:
                        precision = meta.getPrecision(i);
                        System.out.println(columnName + "\t" + jdbcType + "\t" + columnTypeName + "(" + precision + ")" + "\t" + displaySize);
                        break;
                    default:
                        System.out.println(columnName + "\t" + jdbcType + "\t" + columnTypeName + "\t" + displaySize);
                }
            }
        }
    }
}
