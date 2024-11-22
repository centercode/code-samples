package io.github.centercode.sample;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPSample {

    private static final Logger LOG = LoggerFactory.getLogger(HikariCPSample.class);

    public static void main(String[] args) throws MalformedURLException, SQLException {
        setDerbyHome();
        ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
        URL url = new URL("https://maven.aliyun.com/repository/public/org/apache/derby/derby/10.12.1.1/derby-10.12.1.1.jar");
        URLClassLoader ctxLoader = new URLClassLoader(new URL[]{url}, null);
        Thread.currentThread().setContextClassLoader(ctxLoader);
        try {
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
            config.setJdbcUrl("jdbc:derby:default;create=true");
            config.setUsername(null);
            config.setPassword(null);
            config.setMaximumPoolSize(8);
            config.setMinimumIdle(0);
            config.setIdleTimeout(1000);
            config.setConnectionTimeout(1000);
            config.setRegisterMbeans(true);
            try (
                    HikariDataSource dataSource = new HikariDataSource(config);
                    Connection connection = dataSource.getConnection();
            ) {
                LOG.info("Create connection Successful.");
            }
        } finally {
            Thread.currentThread().setContextClassLoader(oldLoader);
        }
    }

    static void setDerbyHome() {
        String name = HikariCPSample.class.getName().replace('.', '/') + ".class";
        URL classUrl = HikariCPSample.class.getClassLoader().getResource(name);
        try {
            String classUrlPath = classUrl.getPath();
            String rootDir = classUrlPath.substring(0, classUrlPath.length() - name.length());
            String dir = Paths.get(new URL(classUrl.getProtocol(), null, rootDir).toURI()).getParent().toString();
            System.setProperty("derby.system.home", dir + "/derby");
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
