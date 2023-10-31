package io.github.centercode.algorithm.number;

import org.junit.Assert;
import org.junit.Test;

public class UglyNumberTest {
    UglyNumber uglyNumber = new UglyNumber();

    @Test
    public void solution1() {
        Assert.assertEquals(1, uglyNumber.solution1(1));
        Assert.assertEquals(2, uglyNumber.solution1(2));
        Assert.assertEquals(3, uglyNumber.solution1(3));
        Assert.assertEquals(4, uglyNumber.solution1(4));
        Assert.assertEquals(5, uglyNumber.solution1(5));
        Assert.assertEquals(6, uglyNumber.solution1(6));
        Assert.assertEquals(8, uglyNumber.solution1(7));
        Assert.assertEquals(9, uglyNumber.solution1(8));
        Assert.assertEquals(10, uglyNumber.solution1(9));
        Assert.assertEquals(12, uglyNumber.solution1(10));
    }
}