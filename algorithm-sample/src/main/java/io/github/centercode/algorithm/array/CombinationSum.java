package io.github.centercode.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CombinationSum {

    /**
     * 39. 组合总和：
     * 给你一个无重复元素的整数数组candidates和一个目标整数target，找出candidates中可以使数字和为目标数target的所有不同组合。
     * candidates中的同一个数字可以无限制重复被选取 。
     */
    public List<List<Integer>> case1Solution1(int[] candidates, int target) {
        // 结果列表
        ArrayList<List<Integer>> res = new ArrayList<>();
        // 状态子集
        ArrayList<Integer> state = new ArrayList<>();
        Arrays.sort(candidates);
        case1Solution1Backtrack(candidates, target, 0, state, res);
        return res;
    }

    private void case1Solution1Backtrack(
        int[] candidates,
        int target,
        int start,
        List<Integer> state,
        List<List<Integer>> res
    ) {
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 剪枝一：数组已排序，后边元素更大，子集和一定超过 target
            if (candidates[i] > target) {
                break;
            }
            state.add(candidates[i]);
            case1Solution1Backtrack(candidates, target - candidates[i], i, state, res);
            state.remove(state.size() - 1);
        }
    }

    /**
     * 40. 组合总和 II：
     * 给定一个候选人编号的集合candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
     * candidates中的每个数字在每个组合中只能使用一次。
     */
    public List<List<Integer>> case2Solution1(int[] candidates, int target) {
        // 结果列表
        ArrayList<List<Integer>> res = new ArrayList<>();
        // 状态子集
        ArrayList<Integer> state = new ArrayList<>();
        Arrays.sort(candidates);
        case2Solution1Backtrack(candidates, target, 0, state, res);
        return res;
    }

    private void case2Solution1Backtrack(
        int[] candidates,
        int target,
        int start,
        List<Integer> state,
        List<List<Integer>> res
    ) {
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            state.add(candidates[i]);
            case2Solution1Backtrack(candidates, target - candidates[i], i + 1, state, res);
            state.remove(state.size() - 1);
        }
    }
}
