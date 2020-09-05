package io.github.centercode.algorithm.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class MinInRotateArrayTest {

    private int[] num;
    private int min;

    public MinInRotateArrayTest(int[] num, int min) {
        this.num = num;
        this.min = min;
    }

    @Parameterized.Parameters
    public static Object[] primeNumbers() {
        return new Object[][]{
                {new int[]{3, 4, 5, 1, 2}, 1},
                {new int[]{2, 2, 2, 0, 1}, 0},
                {new int[]{1, 3, 5}, 1},
        };
    }

    @Test
    public void solution1() {
        MinInRotateArray minInRotateArray = new MinInRotateArray();
        int val = minInRotateArray.solution1(num);
        Assert.assertEquals(min, val);
    }
}