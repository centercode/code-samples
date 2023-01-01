package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesSample {

    public static void main(String[] args) throws IOException {
        PropertiesSample sample = new PropertiesSample();
        sample.loadProperties();
        sample.storeProperties();
    }

    void loadProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream in = getClass().getResourceAsStream("/sample.properties")) {
            properties.load(in);
            getProperties(properties);
            iterateValues(properties);
        }
    }

    private void getProperties(Properties properties) {
        System.out.println("names=" + properties.stringPropertyNames());
        System.out.println("sample.k1=" + properties.getProperty("sample.k1"));
        System.out.println("sample.k3=" + properties.getProperty("sample.k3"));
        System.out.println("sample.k4=" + properties.getProperty("sample.k4", "Not Found"));
    }

    private void iterateValues(Properties properties) {
        Enumeration<Object> elements = properties.elements();
        while (elements.hasMoreElements()) {
            Object o = elements.nextElement();
            System.out.println("element:" + o);
        }
    }

    void storeProperties() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("redis.server.address", "127.0.0.1");
        properties.setProperty("redis.server.port", "6379");
        properties.setProperty("redis.server.password", "");
        properties.setProperty("redis.server.timeout", "2000");
        Path path = Files.createTempFile("sample-", ".properties");
        try (OutputStream out = new FileOutputStream(path.toFile());
             OutputStreamWriter writer = new OutputStreamWriter(out, "GBK")) {
            properties.store(writer, "缓存文件配置测试");
            System.out.println("store to properties: " + path);
        }
    }
}
