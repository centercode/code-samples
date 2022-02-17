package io.github.centercode.sample;

import org.junit.Assert;
import org.junit.Test;

public class WordCountTest {

    @Test
    public void count() {
        WordCount wordCount = new WordCount();
        int count = wordCount.count("hello world");
        Assert.assertEquals(2, count);
    }
}