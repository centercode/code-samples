package io.github.centercode.algorithm.tree;

public class IsMirrorTree {

    public TreeNode solution(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode result = new TreeNode(root.val);
        result.right = solution(root.left);
        result.left = solution(root.right);
        return result;
    }

}
