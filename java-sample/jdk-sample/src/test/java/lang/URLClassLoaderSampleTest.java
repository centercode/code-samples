package lang;

import org.junit.Test;

import java.net.URL;
import java.net.URLClassLoader;

public class URLClassLoaderSampleTest {

    @Test
    public void load() throws Exception {
        String objectFile = "https://repo1.maven.org/maven2/com/zaxxer/HikariCP/3.0.0/HikariCP-3.0.0.jar";
        URL[] urls = {new URL("jar:" + objectFile + "!/")};
        try (URLClassLoader classLoader = URLClassLoader.newInstance(urls)) {
            classLoader.loadClass("com.zaxxer.hikari.HikariDataSource").newInstance();
        }
    }
}