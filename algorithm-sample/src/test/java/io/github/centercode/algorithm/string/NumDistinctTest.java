package io.github.centercode.algorithm.string;

import org.junit.Assert;
import org.junit.Test;

public class NumDistinctTest {

    @Test
    public void solution1() {
        NumDistinct numDistinct = new NumDistinct();
        Assert.assertEquals(3, numDistinct.solution1("rabbbit", "rabbit"));
    }
}