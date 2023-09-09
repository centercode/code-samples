package io.github.centercode.algorithm.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LevelTraverseTest {

    LevelTraverse levelTraverse = new LevelTraverse();

    @Test
    public void solution1() {
        TreeNode n9 = new TreeNode(9);
        TreeNode n3 = new TreeNode(3);
        TreeNode n20 = new TreeNode(20);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);
        n3.left = n9;
        n3.right = n20;
        n20.left = n15;
        n20.right = n7;
        int[] result = levelTraverse.case1Solution1(n3);
        Assert.assertArrayEquals(new int[]{3, 9, 20, 15, 7}, result);
    }

    @Test
    public void case3solution1() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> result = levelTraverse.case3solution1(root);
        System.out.println(result);
    }
}