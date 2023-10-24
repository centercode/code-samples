package io.github.centercode.algorithm.tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class BSTFromPreorderTest {
    BSTFromPreorder bstFromPreorder = new BSTFromPreorder();

    @Test
    public void solution1() {
        int[] preOrder = {8, 5, 1, 7, 10, 12};
        TreeNode root = bstFromPreorder.solution1(preOrder);
        assertEquals(8, root.val);
        assertEquals(5, root.left.val);
        assertEquals(10, root.right.val);
        assertEquals(12, root.right.right.val);
    }
}