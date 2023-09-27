package io.github.centercode.algorithm.sort;

/**
 * 插入排序
 */
public class InsertSort {

    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int base = nums[i], j = i - 1;
            while (j >= 0 && nums[j] > base) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = base;
        }
    }
}
