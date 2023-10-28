package io.github.centercode.algorithm.number;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;

public class SubsetsTest {

    @Test
    public void case1Solution1() {
        Subsets subsets = new Subsets();
        int[] data = {1, 2, 3};
        List<List<Integer>> res = subsets.case1Solution1(data);
        assertEquals(8, res.size());
    }

    @Test
    public void case1Solution2() {
        Subsets subsets = new Subsets();
        int[] data = {1, 2, 3};
        List<List<Integer>> res = subsets.case1Solution2(data);
        assertEquals(8, res.size());
    }

    @Test
    public void case2Solution1() {
        Subsets subsets = new Subsets();
        int[] data = {1, 2, 2};
        List<List<Integer>> res = subsets.case2Solution1(data);
        assertEquals(6, res.size());

        data = new int[] {0};
        res = subsets.case2Solution1(data);
        assertEquals(2, res.size());
    }

}