package io.github.centercode.algorithm.array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合：给定两个整数n和k，返回范围[1, n]中所有可能的k个数的组合。
 */
public class Combine {

    public List<List<Integer>> solution1(int n, int k) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        ArrayDeque<Integer> state = new ArrayDeque<>();
        backtrack(res, state, 1, n, k);
        return res;
    }

    private void backtrack(List<List<Integer>> res, ArrayDeque<Integer> state, int start, int n, int k) {
        if (k == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        for (int i = start; i <= n; i++) {
            state.push(i);
            backtrack(res, state, i + 1, n, k - 1);
            state.pop();
        }
    }

}
