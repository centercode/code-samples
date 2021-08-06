package io.github.centercode.algorithm.array;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 */
public class ExchangeOddEven {

    /**
     * 首尾双指针
     */
    public int[] solution1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int s = 0, e = nums.length - 1;

        while (s < e) {
            while (s < e && isOdd(nums[s])) {
                s++;
            }

            while (s < e && !isOdd(nums[e])) {
                e--;
            }
            if (s < e) {
                swap(nums, s, e);
            }
        }
        return nums;
    }

    /**
     * 快慢双指针
     */
    public int[] solution2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (isOdd(nums[fast])) {
                swap(nums, fast, slow);
                slow++;
            }
            fast++;
        }

        return nums;
    }

    private boolean isOdd(int num) {
        return (num & 0x1) == 1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
