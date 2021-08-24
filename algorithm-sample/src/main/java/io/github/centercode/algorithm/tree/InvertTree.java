package io.github.centercode.algorithm.tree;

/**
 * 翻转一棵二叉树
 */
public class InvertTree {

    public TreeNode solution1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = solution1(root.left);
        TreeNode right = solution1(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
