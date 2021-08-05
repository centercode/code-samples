package io.github.centercode.algorithm.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class IsMirrorTreeTest {

    @Test
    public void mirrorTree() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t9 = new TreeNode(9);

        t7.left = t9;
        t7.right = t6;
        t2.left = t3;
        t2.right = t1;
        t4.left = t7;
        t4.right = t2;

        List<Integer> pre1 = TreeNode.preOrderTraverse(t4);
        Assert.assertEquals(Arrays.asList(4, 7, 9, 6, 2, 3, 1), pre1);
        IsMirrorTree isMirrorTree = new IsMirrorTree();
        TreeNode result = isMirrorTree.solution(t4);
        List<Integer> pre2 = TreeNode.preOrderTraverse(result);
        Assert.assertEquals(Arrays.asList(4, 2, 1, 3, 7, 6, 9), pre2);

    }
}