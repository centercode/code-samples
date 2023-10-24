package io.github.centercode.algorithm.array;

import java.util.ArrayList;
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

}
