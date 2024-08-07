package io.github.centercode;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 通过自定义Classloader加载JDBC驱动类创建Connection，实现多版本的驱动类隔离
 */
public class DriverClassIsolation {

    String url = System.getProperty("url");

    Properties properties = new Properties();

    {
        properties.put("user", System.getProperty("user"));
        properties.put("password", System.getProperty("password"));
    }

    public static void main(String[] args) throws Exception {
        DriverClassIsolation isolation = new DriverClassIsolation();
        isolation.solution1();
        isolation.solution2();
    }

    /**
     * 通过设置 ContextClassLoader，并反射调用 {@link DriverManager#getConnection(String, Properties, Class)} 创建Connection
     */
    void solution1() throws Exception {
        // URL format: {@link java.net.URLConnection}
        URL jarUrl = new URL("jar:https://maven.aliyun.com/repository/public/org/opengauss/opengauss-jdbc/5.0.2/opengauss-jdbc-5.0.2.jar!/");
        URLClassLoader jarLoader = new URLClassLoader(new URL[]{jarUrl}, null);
        Thread.currentThread().setContextClassLoader(jarLoader);
        // DriverManager#getConnection(String, Properties, Class) 是私有方法，只能通过反射调用
        Method method = DriverManager.class.getDeclaredMethod("getConnection", String.class, Properties.class, Class.class);
        method.setAccessible(true);
        try (Connection connection = (Connection) method.invoke(null, url, properties, null)) {
        }
    }

    /**
     * 通过直接反射实例化Driver类创建Connection
     */
    void solution2() throws Exception {
        URL jarUrl = new URL("https://maven.aliyun.com/repository/public/org/opengauss/opengauss-jdbc/5.0.2/opengauss-jdbc-5.0.2.jar");
        URLClassLoader jarLoader = new URLClassLoader(new URL[]{jarUrl}, null);
        Class<Driver> JDBCSampleClass = (Class<Driver>) jarLoader.loadClass("org.postgresql.Driver");
        Driver driver = JDBCSampleClass.newInstance();
        try (Connection connection = driver.connect(url, properties)) {
        }
    }
}
