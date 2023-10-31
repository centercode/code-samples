package io.github.centercode.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 全排列
 */
public class Permute {
    /**
     * 46. 全排列：给定一个不含重复数字的数组nums，返回其所有可能的全排列 。你可以按任意顺序返回答案。
     * 解法一：回溯法
     * tc: O(n∙!n) sc: O(n)
     */
    public List<List<Integer>> case1Solution1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> permutation = new ArrayList<>();
        for (int n : nums) {
            permutation.add(n);
        }
        case1Backtrack(res, permutation, 0);
        return res;
    }

    private void case1Backtrack(List<List<Integer>> res, ArrayList<Integer> permutation, int i) {
        if (i == permutation.size() - 1) {
            res.add(new ArrayList<>(permutation));
            return;
        }
        for (int j = i; j < permutation.size(); j++) {
            Collections.swap(permutation, j, i);
            case1Backtrack(res, permutation, i + 1);
            Collections.swap(permutation, j, i);
        }
    }

    /**
     * 47. 全排列 II：给定一个可包含重复数字的序列nums ，按任意顺序返回所有不重复的全排列。
     */
    public List<List<Integer>> case2Solution1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> permutation = new ArrayList<>();
        for (int n : nums) {
            permutation.add(n);
        }
        case2Backtrack(res, permutation, 0);
        return res;
    }

    private void case2Backtrack(List<List<Integer>> res, ArrayList<Integer> permutation, int i) {
        if (i == permutation.size() - 1) {
            res.add(new ArrayList<>(permutation));
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int j = i; j < permutation.size(); j++) {
            if (set.contains(permutation.get(j))) {
                continue;
            }
            set.add(permutation.get(j));
            Collections.swap(permutation, j, i);
            case2Backtrack(res, permutation, i + 1);
            Collections.swap(permutation, j, i);
        }
    }

    /**
     * 60. 排列序列：
     * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
     * 给定 n 和 k，返回第 k 个排列。
     */
    public String case3Solution1(int n, int k) {
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        // 计算阶乘数组
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        boolean[] used = new boolean[n + 1];
        Arrays.fill(used, false);
        StringBuilder path = new StringBuilder();
        case3Dfs(n, k, used, factorial, n - 1, path);
        return path.toString();
    }

    /**
     * @param remains 剩余未固定位数
     */
    private void case3Dfs(int n, int k, boolean[] used, int[] factorial, int remains, StringBuilder path) {
        if (remains < 0) {
            return;
        }
        int cnt = factorial[remains];
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (k > cnt) {
                k -= cnt;
                continue;
            }
            path.append(i);
            used[i] = true;
            case3Dfs(n, k, used, factorial, remains - 1, path);
            return;
        }
    }

}
