package io.github.centercode.algorithm.tree;

/**
 * 1008. 前序遍历构造二叉搜索树
 */
public class BSTFromPreorder {

    TreeNode solution1(int[] preorder) {
        return dfs(preorder, 0, preorder.length - 1);
    }

    /**
     * left include, right include
     */
    TreeNode dfs(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[left]);
        if (left == right) {
            return root;
        }
        // 或者调用biSearchLeftEnd()二分查找LeftEnd分界点
        int rightStart = left;
        for (; rightStart <= right; rightStart++) {
            if (preorder[rightStart] > preorder[left]) {
                break;
            }
        }
        TreeNode leftNode = dfs(preorder, left + 1, rightStart - 1);
        TreeNode rightNode = dfs(preorder, rightStart, right);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    /**
     * 在区间[left..right]里找最后一个小于 preorder[left]的下标
     * 注意这里设置区间的左边界为left，不能是left + 1
     * 这是因为考虑到区间只有2个元素[left, right]的情况，第1个部分为空区间，第2部分只有一个元素right
     */
    int biSearchLeftEnd(int[] preorder, int left, int right) {
        int l = left;
        int r = right;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (preorder[mid] < preorder[left]) {
                // 下一轮搜索区间是 [mid, r]
                l = mid;
            } else {
                // 下一轮搜索区间是 [l, mid - 1]
                r = mid - 1;
            }
        }
        return l;
    }
}
