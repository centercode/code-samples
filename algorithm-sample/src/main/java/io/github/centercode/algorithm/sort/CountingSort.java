package io.github.centercode.algorithm.sort;

/**
 * 计数排序：本质上是桶排序在整型数据下的一个特例。
 */
public class CountingSort {

    void sort(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        // 1. 统计数组最大元素 max
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        // 2. 统计各数字的出现次数
        int[] counter = new int[max + 1];
        for (int num : nums) {
            counter[num]++;
        }
        // 3. 遍历 counter ，将各元素填入原数组 nums
        int i = 0;
        for (int num = 0; num < counter.length; num++) {
            while (counter[num] > 0) {
                nums[i++] = num;
                counter[num]--;
            }
        }
    }
}
