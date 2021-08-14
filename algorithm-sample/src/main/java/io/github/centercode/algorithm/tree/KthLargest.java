package io.github.centercode.algorithm.tree;

class KthLargest {
    int res, k;

    public int solution(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (k > 0) {
            dfs(root.right);
        }
        if (--k == 0) {
            res = root.val;
            return;
        }
        dfs(root.left);
    }
}