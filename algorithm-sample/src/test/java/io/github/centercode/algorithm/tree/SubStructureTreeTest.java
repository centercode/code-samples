package io.github.centercode.algorithm.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubStructureTreeTest {

    @Test
    public void solution() {

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t3 = new TreeNode(3);

        t4.left = t1;
        t4.right = t2;
        t3.left = t4;
        t3.right = t5;


        SubStructureTree subStructureTree = new SubStructureTree();
        boolean solution = subStructureTree.solution(t3, t4);
        assertTrue(solution);
    }
}