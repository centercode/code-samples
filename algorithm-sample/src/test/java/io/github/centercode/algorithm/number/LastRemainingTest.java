package io.github.centercode.algorithm.number;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class LastRemainingTest {

    LastRemaining lastRemaining = new LastRemaining();

    int n;
    int m;
    int expect;

    public LastRemainingTest(int n, int m, int expect) {
        this.n = n;
        this.m = m;
        this.expect = expect;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
            {5, 3, 3},
            {5, 2, 2}
        };
    }

    @Test
    public void solution() {
        Assert.assertEquals(expect, lastRemaining.solution1(n, m));
        Assert.assertEquals(expect, lastRemaining.solution2(n, m));
        Assert.assertEquals(expect, lastRemaining.solution3(n, m));
    }
}