package io.github.centercode.tree;

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
                inOrder, 0, inOrder.length - 1, indexMap);
    }

    private TreeNode solution1(int[] preOrder, int preStart, int preEnd,
                               int[] inOrder, int inStart, int inEnd, Map<Integer, Integer> indexMap) {
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
                inOrder, inStart, inRoot - 1, indexMap);
        TreeNode right = solution1(preOrder, preStart + leftLen, preEnd,
                inOrder, inRoot + 1, inEnd, indexMap);
        rootNode.left = left;
        rootNode.right = right;
        return rootNode;
    }
}

