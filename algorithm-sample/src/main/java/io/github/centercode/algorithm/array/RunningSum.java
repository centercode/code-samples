package io.github.centercode.algorithm.array;

/**
 * 一维数组的动态和
 */
class RunningSum {

    public int[] solution1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] res = new int[nums.length];
        res[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] + nums[i];
        }
        return res;
    }
}