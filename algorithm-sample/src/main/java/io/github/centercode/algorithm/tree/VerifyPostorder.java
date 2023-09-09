package io.github.centercode.algorithm.tree;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 */
public class VerifyPostorder {

    public boolean solution1(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    /**
     * 递归分治
     * tc: O(n^2), sc: O(N)
     */
    boolean recur(int[] postorder, int i, int j) {
        if (i >= j) {
            return true;
        }
        int p = i;
        while (postorder[p] < postorder[j]) {
            p++;
        }
        int m = p;
        while (postorder[p] > postorder[j]) {
            p++;
        }
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    /**
     * 辅助单调栈: TODO
     */
    public boolean solution2(int[] postorder) {
        return false;
    }
}
