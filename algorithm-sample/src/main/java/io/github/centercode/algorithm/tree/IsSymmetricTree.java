package io.github.centercode.algorithm.tree;

/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。
 * 如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
public class IsSymmetricTree {

    public boolean solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recur(root.left, root.right);
    }

    private boolean recur(TreeNode l, TreeNode r) {
        if (l == null && r == null) {
            return true;
        }
        if (l == null || r == null || l.val != r.val) {
            return false;
        }
        return recur(l.left, r.right) && recur(l.right, r.left);
    }

}
