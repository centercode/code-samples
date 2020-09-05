package io.github.centercode.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "{" + val + '}';
    }

    public static List<Integer> preOrderTraverse(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        preOrderTraverse(result, node);
        return result;
    }

    static void preOrderTraverse(List<Integer> result, TreeNode node) {
        if (node != null) {
            result.add(node.val);
            preOrderTraverse(result, node.left);
            preOrderTraverse(result, node.right);
        }
    }
}