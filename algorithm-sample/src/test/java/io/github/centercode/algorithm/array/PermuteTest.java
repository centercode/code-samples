package io.github.centercode.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

public class PermuteTest {
    Permute permute = new Permute();

    @Test
    public void case3Solution1() {
        Assert.assertEquals("123", permute.case3Solution1(3, 1));
        Assert.assertEquals("213", permute.case3Solution1(3, 3));
        Assert.assertEquals("2314", permute.case3Solution1(4, 9));
    }
}