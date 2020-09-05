package io.github.centercode.algorithm.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 */
public class RebuildBiTree {

    public TreeNode solution1(int[] preOrder, int[] inOrder) {
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            indexMap.put(inOrder[i], i);
        }
        return solution1(preOrder, 0, preOrder.length - 1,
                0, indexMap);
    }

    private TreeNode solution1(int[] preOrder, int preStart, int preEnd,
                               int inStart, Map<Integer, Integer> indexMap) {
        if (preStart > preEnd) {
            return null;
        }
        int rootVal = preOrder[preStart];
        TreeNode rootNode = new TreeNode(rootVal);
        if (preStart == preEnd) {
            return rootNode;
        }
        int inRoot = indexMap.get(rootVal);
        int leftLen = inRoot - inStart + 1;
        TreeNode left = solution1(preOrder, preStart + 1, preStart + leftLen - 1,
                inStart, indexMap);
        TreeNode right = solution1(preOrder, preStart + leftLen, preEnd,
                inRoot + 1, indexMap);
        rootNode.left = left;
        rootNode.right = right;
        return rootNode;
    }

    //############## Solution2 一次遍历，O(n)

    private int in = 0;
    private int pre = 0;

    public TreeNode solution2(int[] preOrder, int[] inOrder) {
        return solution2(preOrder, inOrder, Integer.MIN_VALUE);
    }

    private TreeNode solution2(int[] preOrder, int[] inOrder, int stop) {
        if (pre >= preOrder.length)
            return null;
        if (inOrder[in] == stop) {
            in++;
            return null;
        }
        TreeNode node = new TreeNode(preOrder[pre++]);
        node.left = solution2(preOrder, inOrder, node.val);
        node.right = solution2(preOrder, inOrder, stop);
        return node;
    }
}

