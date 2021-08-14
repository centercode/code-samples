package io.github.centercode.algorithm.array;

/**
 * 统计一个数字在排序数组中出现的次数
 */
public class CountInSortedArray {

    public int solution1(int[] nums, int target) {
        // 查找左边界
        int left = binSearch(nums, 0, nums.length - 1, target, true);
        if (left == -1) {
            return 0;
        }
        // 查找右边界
        int right = binSearch(nums, left, nums.length - 1, target, false);

        return right - left + 1;
    }

    private int binSearch(int[] nums, int i, int j, int target, boolean boundLeft) {
        int ans = -1;
        while (i <= j) {
            int m = i + (j - i) / 2;
            if (nums[m] < target) {
                i = m + 1;
            } else if (nums[m] == target) {
                if (boundLeft) {
                    j = m - 1;
                } else {
                    i = m + 1;
                }
                ans = m;
            } else {
                j = m - 1;
            }

        }

        return ans;
    }
}