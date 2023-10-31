package io.github.centercode.algorithm.number;

import java.util.ArrayList;

/**
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 */
public class FindContinuousSequence {

    public int[][] solution1(int target) {
        ArrayList<int[]> result = new ArrayList<>();
        for (int n = target / 2; n > 1; n--) {
            if (n * (n + 1) > 2 * target) {
                continue;
            }
            // x: 左边界
            int x = (target - n * (n - 1) / 2) / n;
            if (x > 0 && (2 * x + n - 1) * n == target * 2) {
                result.add(fill(x, n));
            }
        }
        return result.toArray(new int[0][0]);
    }

    int[] fill(int start, int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = start + i;
        }
        return arr;
    }

    /**
     * 滑动窗口（双指针法）
     * tc: O(n) sc: O(1)
     */
    public int[][] solution2(int target) {
        ArrayList<int[]> result = new ArrayList<>();
        int i = 1, j = 2;
        while (i < j) {
            int sum = (i + j) * (j - i + 1) / 2;
            if (sum < target) {
                j++;
            } else if (sum == target) {
                result.add(fill(i, j - i + 1));
                i++;
            } else {
                i++;
            }
        }
        return result.toArray(new int[0][]);
    }

}
