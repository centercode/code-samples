package io.github.centercode.algorithm.tree;

public class SubStructureTree {

    public boolean solution(TreeNode A, TreeNode B) {
        return isSubStructure(A, B);

    }

    private boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (contains(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    private boolean contains(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }

        return contains(A.left, B.left) && contains(A.right, B.right);
    }
}
