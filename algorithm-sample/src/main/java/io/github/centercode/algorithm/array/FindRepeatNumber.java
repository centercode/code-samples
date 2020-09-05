package io.github.centercode.algorithm.array;

/**
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 */
public class FindRepeatNumber {

    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < nums.length; i++) {
            while (i != nums[i]) {
                rangeCheck(nums, nums[i]);
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                // let nums[nums[i]] = nums[i]
                swap(nums, i, nums[i]);
            }
        }
        throw new IllegalStateException("Not found");
    }

    void swap(int[] nums, int i, int j) {
        int tmp = nums[i] ^ nums[j];
        nums[i] = nums[j];
        nums[j] = nums[j] ^ tmp;
    }

    void rangeCheck(int[] nums, int j) {
        if (j < 0 || nums.length <= j) {
            throw new IllegalArgumentException();
        }
    }
}
