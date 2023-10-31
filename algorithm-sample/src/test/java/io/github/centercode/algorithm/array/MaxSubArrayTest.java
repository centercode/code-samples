package io.github.centercode.algorithm.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class MaxSubArrayTest {
    int[] nums;
    int maxSum;

    public MaxSubArrayTest(int[] nums, int maxSum) {
        this.nums = nums;
        this.maxSum = maxSum;
    }

    @Parameterized.Parameters
    public static Object[] parameters() {
        return new Object[][]{
                {new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6},
                {new int[]{-2, -3, -4, -5}, -2},
                {new int[]{1, -2, 3, 10, -4, 7, 2, -5}, 18},
        };
    }

    @Test
    public void solution1() {
        MaxSubArray maxSubArray = new MaxSubArray();
        int maxSum = maxSubArray.solution1(nums);
        Assert.assertEquals(this.maxSum, maxSum);
    }
}