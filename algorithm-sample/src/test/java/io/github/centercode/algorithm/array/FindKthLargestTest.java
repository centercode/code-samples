package io.github.centercode.algorithm.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class FindKthLargestTest {
    FindKthLargest findKthLargest = new FindKthLargest();
    int[] nums;
    int k;
    int topK;

    public FindKthLargestTest(int[] nums, int k, int topK) {
        this.nums = nums;
        this.k = k;
        this.topK = topK;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
            {new int[] {2,1}, 2, 1},
            {new int[] {3, 2, 1, 5, 6, 4}, 2, 5},
            {new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6}, 4, 4},
        };
    }

    @Test
    public void solution1() {
        Assert.assertEquals(topK, findKthLargest.solution1(nums, k));
    }

    @Test
    public void solution2() {
        Assert.assertEquals(topK, findKthLargest.solution2(nums, k));
    }
}