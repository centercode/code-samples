package io.github.centercode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FibonacciTest {

    private int n;

    private int expect;

    public FibonacciTest(int n, int expect) {
        this.n = n;
        this.expect = expect;
    }

    @Parameterized.Parameters
    public static Object[] parameters() {
        return new Object[][]{
                new Object[]{0, 0},
                new Object[]{1, 1},
                new Object[]{2, 1},
                new Object[]{45, 134903163},
                new Object[]{48, 807526948},
        };
    }

    @Test
    public void solution1() {
        Fibonacci fibonacci = new Fibonacci();
        int i = fibonacci.solution1(n);
        Assert.assertEquals(expect, i);
    }
}