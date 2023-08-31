package io.github.centercode.algorithm.tree;

public class IsBalanceTree {
    public boolean solution1(TreeNode root) {
        return depth(root) >= 0;
    }

    int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        if (leftDepth == -1
                || rightDepth == -1
                || Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
