package io.github.centercode.algorithm.array;

/**
 * 852. 山脉数组的峰顶索引
 */
public class PeakIndexInMountainArray {

    /**
     * 解法一：二分查找
     */
    public int solution1(int[] arr) {
        int left = 0, right = arr.length - 2;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
