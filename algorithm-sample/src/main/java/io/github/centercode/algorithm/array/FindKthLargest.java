package io.github.centercode.algorithm.array;

import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。1 <= k
 * <p>
 * Reference: https://github.com/yankuangshi/leetcode/issues/3
 */
public class FindKthLargest {

    /**
     * 维护大小为K的小顶堆
     * tc: O(nlogk)
     */
    public int solution1(int[] nums, int k) {
        if (k > nums.length || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(nums.length);
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

    /**
     * 快速选择算法
     */
    public int solution2(int[] nums, int k) {
        int n = nums.length;
        return quickSelect(nums, 0, n - 1, n - k);
    }

    int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[k];
        }
        int x = nums[left], i = left - 1, j = right + 1;
        while (i < j) {
            do {
                i++;
            } while (nums[i] < x);
            do {
                j--;
            } while (nums[j] > x);
            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j) {
            return quickSelect(nums, left, j, k);
        } else {
            return quickSelect(nums, j + 1, right, k);
        }
    }

}
