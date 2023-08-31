package io.github.centercode.algorithm.number;

import org.junit.Assert;
import org.junit.Test;

public class AdderTest {

    @Test
    public void solution1() {
        Adder adder = new Adder();
        Assert.assertEquals(8, adder.solution1(3, 5));
        Assert.assertEquals(5, adder.solution1(0, 5));
        Assert.assertEquals(2, adder.solution1(-3, 5));
        Assert.assertEquals(-7, adder.solution1(-3, -4));
    }
}