package io;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;

public class ClasspathResourceSampleTest {

    @Test
    public void getUnifiedResourcePath() {
        ClasspathResourceSample sample = new ClasspathResourceSample();
        Path path = sample.getUnifiedResourcePath();
        String expect = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";
        assertEquals(expect, path.toString());
    }
}