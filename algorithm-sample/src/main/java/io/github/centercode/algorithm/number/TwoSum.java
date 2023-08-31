package io.github.centercode.algorithm.number;

/**
 * 剑指 Offer 57. 和为s的两个数字
 */
public class TwoSum {
    public int[] solution1(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {
                return new int[]{nums[i], nums[j]};
            }
        }
        return null;
    }
}
