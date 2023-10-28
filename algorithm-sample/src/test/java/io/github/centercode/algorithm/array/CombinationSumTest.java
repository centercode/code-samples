package io.github.centercode.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CombinationSumTest {
    CombinationSum combinationSum = new CombinationSum();

    @Test
    public void case1Solution1() {
        int[] data = {2, 3, 6, 7};
        List<List<Integer>> res = combinationSum.case1Solution1(data, 7);
        Assert.assertEquals(2, res.size());
        Assert.assertTrue(res.contains(Arrays.asList(2, 2, 3)));
        Assert.assertTrue(res.contains(Collections.singletonList(7)));

        data = new int[] {2, 3, 5};
        res = combinationSum.case1Solution1(data, 8);
        Assert.assertEquals(3, res.size());
        Assert.assertTrue(res.contains(Arrays.asList(2, 2, 2, 2)));
        Assert.assertTrue(res.contains(Arrays.asList(2, 3, 3)));
        Assert.assertTrue(res.contains(Arrays.asList(3, 5)));

        data = new int[] {2};
        res = combinationSum.case1Solution1(data, 1);
        Assert.assertEquals(0, res.size());
    }

    @Test
    public void case2Solution1() {
        int[] data = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> res = combinationSum.case2Solution1(data, 8);
        Assert.assertEquals(4, res.size());
        Assert.assertTrue(res.contains(Arrays.asList(1, 1, 6)));
        Assert.assertTrue(res.contains(Arrays.asList(1, 2, 5)));
        Assert.assertTrue(res.contains(Arrays.asList(1, 7)));
        Assert.assertTrue(res.contains(Arrays.asList(2, 6)));

    }
}