package io.github.centercode.algorithm.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CodecTest {
    public static void main(String[] args) {
        long sum = 0, n = 0;
        for(int i = 0; i < 10; i++) {
            n = 10 * n + 9;
            sum += n;
        }
        System.out.println(sum);
    }

    @Test
    public void solution1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Codec.Solution1 solution = new Codec.Solution1();
        String data = solution.serialize(root);
        TreeNode rootCopy = solution.deserialize(data);
        assertEquals("1,2,null,null,3,4,null,null,5,null,null,", data);
        assertEquals(root.val, rootCopy.val);
        assertEquals(root.left.val, rootCopy.left.val);
        assertEquals(root.right.val, rootCopy.right.val);

        assertNull(rootCopy.left.left);
        assertNull(rootCopy.left.right);
        assertNull(rootCopy.right.left.left);
        assertNull(rootCopy.right.left.right);

        assertEquals(root.right.left.val, rootCopy.right.left.val);
        assertEquals(root.right.right.val, rootCopy.right.right.val);
    }

}