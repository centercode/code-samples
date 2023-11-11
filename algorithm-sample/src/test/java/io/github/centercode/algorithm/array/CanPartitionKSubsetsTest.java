package io.github.centercode.algorithm.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class CanPartitionKSubsetsTest {
    CanPartitionKSubsets canPartitionKSubsets = new CanPartitionKSubsets();

    @Test
    public void solution1() {
        int[] data = {4, 3, 2, 3, 5, 2, 1};
        assertTrue(canPartitionKSubsets.solution1(data, 4));
        data = new int[] {1, 2, 3, 4};
        assertFalse(canPartitionKSubsets.solution1(data, 3));
    }

    @Test
    public void solution2() {
        int[] data = {4, 3, 2, 3, 5, 2, 1};
        assertTrue(canPartitionKSubsets.solution2(data, 4));
        data = new int[] {1, 2, 3, 4};
        assertFalse(canPartitionKSubsets.solution2(data, 3));
    }
}