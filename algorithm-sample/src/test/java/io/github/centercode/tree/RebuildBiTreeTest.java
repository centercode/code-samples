package io.github.centercode.tree;

import org.junit.Test;

import java.util.List;

public class RebuildBiTreeTest {

    @Test
    public void buildTree() {
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};
        RebuildBiTree tree = new RebuildBiTree();
        TreeNode treeNode = tree.solution1(preOrder, inOrder);
        List<Integer> preOrderTraverse = TreeNode.preOrderTraverse(treeNode);
        System.out.println(preOrderTraverse);
    }
}