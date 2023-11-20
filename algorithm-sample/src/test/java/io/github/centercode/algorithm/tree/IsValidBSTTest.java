package io.github.centercode.algorithm.tree;

import org.junit.Assert;
import org.junit.Test;

public class IsValidBSTTest {

    IsValidBST isValidBST = new IsValidBST();

    @Test
    public void solution1() {
        TreeNode root = null;
        Assert.assertTrue(isValidBST.solution1(root));

        root = new TreeNode(0);
        Assert.assertTrue(isValidBST.solution1(root));

        root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        Assert.assertTrue(isValidBST.solution1(root));

        root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
        Assert.assertFalse(isValidBST.solution1(root));
    }
}