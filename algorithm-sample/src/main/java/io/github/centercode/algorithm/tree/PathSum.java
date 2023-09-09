package io.github.centercode.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 */
public class PathSum {

    Deque<Integer> path = new LinkedList<>();
    List<List<Integer>> ret = new LinkedList<>();

    /**
     * 深度优先搜索
     * tc: O(n), sc: O(n)
     */
    public List<List<Integer>> solution1(TreeNode root, int target) {
        dfs(root, target);
        return ret;
    }

    public void dfs(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) {
            ret.add(new LinkedList<>(path));
        }
        dfs(root.left, target);
        dfs(root.right, target);
        path.pollLast();
    }

}
