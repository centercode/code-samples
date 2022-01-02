package io;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class IOUtilsTest {

    @Test
    public void loadString() throws IOException {
        String str = IOUtils.loadString("log4j.properties");
        Assert.assertNotNull(str);
    }
}