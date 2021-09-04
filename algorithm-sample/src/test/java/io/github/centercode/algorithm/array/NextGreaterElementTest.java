package io.github.centercode.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class NextGreaterElementTest {

    @Test
    public void nextMax() {
        int[] nums = new int[]{1, 2, 1};
        int[] res = new NextGreaterElement().solution(nums);
        Assert.assertArrayEquals(res, new int[]{2, -1, 2});
    }
}