package io.github.centercode.algorithm.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class IsBalanceTreeTest {
    TreeNode root = new TreeNode(3);

    {
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
    }

    @Test
    public void solution1() {
        IsBalanceTree isBalanceTree = new IsBalanceTree();
        assertTrue(isBalanceTree.solution1(root));
    }
}