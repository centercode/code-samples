package io.github.centercode.algorithm.array;

import java.util.Arrays;

/**
 * No.39
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
class MajorityElement {

    /**
     * 摩尔投票法：核心理念为 票数正负抵消
     * 此方法时间和空间复杂度分别为 O(N) 和 O(1)
     */
    public int solution1(int[] nums) {
        int vote = 0, x = 0;
        for (int num : nums) {
            if (vote == 0) {
                x = num;
            }
            if (num == x) {
                vote++;
            } else {
                vote--;
            }
        }

        return x;
    }

    /**
     * 数组下面的线表示如果众数是数组中的最小值时覆盖的下标，
     * 数组上面的线表示如果众数是数组中的最大值时覆盖的下标。
     * 对于其他的情况，这条线会在这两种极端情况的中间。
     * 对于这两种极端情况，它们会在下标为 n/2的地方有重叠。
     */
    public int solution2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}