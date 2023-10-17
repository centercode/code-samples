package io.github.centercode.algorithm.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaxProfitTest {

    MaxProfit maxProfit = new MaxProfit();

    @Test
    public void testCase1Solution1() {
        int[] data = {7, 1, 5, 3, 6, 4};
        assertEquals(5, maxProfit.case1Solution1(data));
    }

    @Test
    public void testCase2Solution1() {
        int[] data = {7, 1, 5, 3, 6, 4};
        assertEquals(7, maxProfit.case2Solution1(data));
        data = new int[] {1, 2, 3, 4, 5};
        assertEquals(4, maxProfit.case2Solution1(data));
        data = new int[] {7, 6, 4, 3, 1};
        assertEquals(0, maxProfit.case2Solution1(data));
    }

    @Test
    public void testCase2Solution2() {
        int[] data = {7, 1, 5, 3, 6, 4};
        assertEquals(7, maxProfit.case2Solution2(data));
        data = new int[] {1, 2, 3, 4, 5};
        assertEquals(4, maxProfit.case2Solution2(data));
        data = new int[] {7, 6, 4, 3, 1};
        assertEquals(0, maxProfit.case2Solution2(data));
    }

    @Test
    public void testCase3Solution1() {
        int[] data = {3, 3, 5, 0, 0, 3, 1, 4};
        assertEquals(6, maxProfit.case3Solution1(data));
        data = new int[] {1, 2, 3, 4, 5};
        assertEquals(4, maxProfit.case3Solution1(data));
        data = new int[] {7, 6, 4, 3, 1};
        assertEquals(0, maxProfit.case3Solution1(data));
    }
}