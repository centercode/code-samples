package io.github.centercode.algorithm.number;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SingleNumbersTest {
    SingleNumbers singleNumbers = new SingleNumbers();

    @Test
    public void case1Solution1() {
        int[] results = singleNumbers.case1Solution1(new int[]{4, 1, 4, 6});
        Arrays.sort(results);
        Assert.assertArrayEquals(new int[]{1, 6}, results);

        results = singleNumbers.case1Solution1(new int[]{1, 2, 10, 4, 1, 4, 3, 3});
        Arrays.sort(results);
        Assert.assertArrayEquals(new int[]{2, 10}, results);
    }

    @Test
    public void case2Solution1() {
        int[] nums = {9, 1, 7, 9, 7, 9, 7};
        Assert.assertEquals(1, singleNumbers.case2Solution1(nums));
    }
}