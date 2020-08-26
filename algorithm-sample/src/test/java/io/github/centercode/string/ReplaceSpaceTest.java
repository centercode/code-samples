package io.github.centercode.string;

import org.junit.Assert;
import org.junit.Test;

public class ReplaceSpaceTest {

    @Test
    public void solution2() {
        ReplaceSpace replaceSpace = new ReplaceSpace();
        String s = replaceSpace.solution2("We are happy.");
        Assert.assertEquals("We%20are%20happy.", s);
    }
}