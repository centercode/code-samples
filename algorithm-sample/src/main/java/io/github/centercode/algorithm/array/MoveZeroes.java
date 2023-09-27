package io.github.centercode.algorithm.array;

/**
 * 283. 移动零
 */
public class MoveZeroes {

    public void solution1(int[] nums) {
        // 指向首个0的左侧
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
        }
    }

    private void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
