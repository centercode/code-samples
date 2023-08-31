package io.github.centercode.algorithm.tree;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 剑指Offer68
 */
public class LowestCommonAncestor {

    /**
     * 二叉搜索树的最近公共祖先
     */
    public TreeNode case1Solution1(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }

    /**
     * 二叉树的最近公共祖先
     */
    public TreeNode case2Solution1(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<Integer, TreeNode> parent = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        dfs(root, parent);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    void dfs(TreeNode root, HashMap<Integer, TreeNode> parentMap) {
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            dfs(root.left, parentMap);
        }
        if (root.right != null) {
            parentMap.put(root.right.val, root);
            dfs(root.right, parentMap);
        }
    }

    /**
     * 二叉树的最近公共祖先
     */
    public TreeNode case2Solution2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = case2Solution2(root.left, p, q);
        TreeNode right = case2Solution2(root.right, p, q);
        if (left == null && right == null) return null; // 1.
        if (left == null) return right; // 3.
        if (right == null) return left; // 4.
        return root; // 2. if(left != null and right != null)
    }

}
