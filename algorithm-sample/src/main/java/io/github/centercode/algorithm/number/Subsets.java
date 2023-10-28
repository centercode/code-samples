package io.github.centercode.algorithm.number;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 子集
 */
public class Subsets {

    /**
     * 78. 子集：给你一个整数数组，数组中的元素互不相同 。返回该数组所有可能的子集。
     * 迭代法：
     * 记原序列中元素的总数为n。原序列中的每个数字a的状态可能有两种，即「在子集中」和「不在子集中」。
     * 我们用1表示「在子集中」，0表示不在子集中，可以发现 0/1 序列对应的二进制数正好从 0 到 2^n−1。
     * 注意：1 <= nums.length <= 10
     */
    public List<List<Integer>> case1Solution1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int mask = 0; mask < (1 << nums.length); mask++) {
            List<Integer> t = new ArrayList<>();
            // 根据mask输出一个子集
            for (int i = 0; i < nums.length; i++) {
                if (((mask >>> i) & 1) == 1) {
                    // 第i位在集合中
                    t.add(nums[i]);
                }
            }
            res.add(t);
        }
        return res;
    }

    /**
     * 递归法
     */
    public List<List<Integer>> case1Solution2(int[] nums) {
        Deque<Integer> q = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, q, res);
        return res;
    }

    private void dfs(int[] nums, int i, Deque<Integer> q, List<List<Integer>> res) {
        if (i == nums.length) {
            res.add(new ArrayList<>(q));
            return;
        }
        // 考虑不选择当前位置
        dfs(nums, i + 1, q, res);
        // 考虑选择当前位置
        q.addLast(nums[i]);
        dfs(nums, i + 1, q, res);
        q.removeLast();
    }

    /**
     * 78. 子集：给你一个整数数组，其中可能包含重复元素，请你返回该数组所有可能的子集。
     * 迭代法：同case1
     * 注意：1 <= nums.length <= 10
     */
    public List<List<Integer>> case2Solution1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int mask = 0; mask < (1 << nums.length); mask++) {
            List<Integer> t = new ArrayList<>();
            boolean valid = true;
            // 根据mask输出一个子集
            for (int i = 0; i < nums.length; i++) {
                if (((mask >>> i) & 1) == 1) {
                    if (i > 0 && (((mask >>> (i - 1)) & 1) == 0) && nums[i] == nums[i - 1]) {
                        valid = false;
                        break;
                    }
                    // 第i位在集合中
                    t.add(nums[i]);
                }
            }
            if (valid) {
                res.add(t);
            }
        }
        return res;
    }
}
