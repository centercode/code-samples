package io.github.centercode.algorithm.dp;

class MaxSubArray {

    /**
     * f(i) 表示以第i个数字结尾的子数组的最大和
     * f(i) = nums(i)           (i == 0 || f(i-1) <= 0)
     * f(i) = nums(i) + f(i-1)  (i != 0 && f(i-1) > 0)
     */
    public int solution1(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        int maxSum = nums[0], result = maxSum;
        for (int i = 1; i < nums.length; i++) {
            if (maxSum <= 0) {
                maxSum = nums[i];
            } else {
                maxSum += nums[i];
            }
            if (result < maxSum) {
                result = maxSum;
            }
        }
        return result;
    }
}