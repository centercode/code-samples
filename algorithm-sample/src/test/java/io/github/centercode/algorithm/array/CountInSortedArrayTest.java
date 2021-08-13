package io.github.centercode.algorithm.array;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CountInSortedArrayTest {

    int[] nums;
    int target;
    int expectCount;

    public CountInSortedArrayTest(int[] nums, int target, int expectCount) {
        this.nums = nums;
        this.target = target;
        this.expectCount = expectCount;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {new int[]{5, 7, 7, 8, 8, 10}, 8, 2},
                {new int[]{5, 7, 7, 8, 8, 10}, 9, 0},
        };
    }

    @Test
    public void solution1() {
        CountInSortedArray countInSortedArray = new CountInSortedArray();
        assertEquals(expectCount, countInSortedArray.solution1(nums, target));
    }
}