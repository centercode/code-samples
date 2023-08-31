package io.github.centercode.algorithm.tree;

public class MaxDepth {

    int solution1(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(solution1(root.left), solution1(root.right)) + 1;
        }
    }
}
