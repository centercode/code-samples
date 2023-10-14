package io.github.centercode.algorithm.tree;

/**
 * 1372. 二叉树中的最长交错路径
 */
public class LongestZigZag {

    int maxPath = 0;

    public int solution1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, false, 0);
        dfs(root, true, 0);
        return maxPath;
    }

    /**
     * @param root      current root node
     * @param direction false for left, true for right
     * @param pathLen   累积经过的路径
     */
    public void dfs(TreeNode root, boolean direction, int pathLen) {
        maxPath = Math.max(maxPath, pathLen);
        if (!direction) {
            if (root.left != null) {
                // 改变方向，pathLen加1
                dfs(root.left, true, pathLen + 1);
            }
            if (root.right != null) {
                // 方向不变，以当前节点为开头重新规划路径
                dfs(root.right, false, 1);
            }
        } else {
            if (root.left != null) {
                dfs(root.left, true, 1);
            }
            if (root.right != null) {
                dfs(root.right, false, pathLen + 1);
            }
        }
    }

}
