package io.github.centercode.algorithm.tree;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxDepthTest {

    @Test
    public void solution1() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        MaxDepth maxDepth = new MaxDepth();
        Assert.assertEquals(3, maxDepth.solution1(root));
    }
}