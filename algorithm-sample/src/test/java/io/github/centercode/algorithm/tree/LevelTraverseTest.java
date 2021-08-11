package io.github.centercode.algorithm.tree;

import org.junit.Assert;
import org.junit.Test;

public class LevelTraverseTest {

    @Test
    public void solution1() {
        TreeNode n9 = new TreeNode(9);
        TreeNode n3 = new TreeNode(3);
        TreeNode n20 = new TreeNode(20);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);
        n3.left =n9;
        n3.right =n20;
        n20.left =n15;
        n20.right=n7;
        LevelTraverse levelTraverse = new LevelTraverse();
        int[] result = levelTraverse.solution1(n3);
        Assert.assertArrayEquals(new int[] {3,9,20,15,7}, result);
    }
}