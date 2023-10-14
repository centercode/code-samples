package io.github.centercode.algorithm.tree;

/**
 * 124. 二叉树中的最大路径和
 */
public class MaxPathSum {

    int maxSum = Integer.MIN_VALUE;

    /**
     * 递归
     * tc: O(n), sc: O(n)
     */
    public int solution1(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    /**
     * @param root 当前根节点
     * @return 该节点到所有叶子节点中最大的路径和
     */
    int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 只有在贡献值大于0时，才会选取
        int leftSum = Math.max(dfs(root.left), 0);
        int rightSum = Math.max(dfs(root.right), 0);
        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int currentMaxSum = leftSum + rightSum + root.val;
        // 更新答案
        maxSum = Math.max(maxSum, currentMaxSum);
        return root.val + Math.max(leftSum, rightSum);
    }
}
