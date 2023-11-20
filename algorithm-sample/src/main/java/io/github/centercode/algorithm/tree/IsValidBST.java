package io.github.centercode.algorithm.tree;

/**
 * 98. 验证二叉搜索树
 */
public class IsValidBST {

    long pre = Long.MIN_VALUE;

    /**
     * 解法一：中序遍历
     */
    public boolean solution1(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!solution1(root.left)) {
            return false;
        }
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        return solution1(root.right);
    }
}
