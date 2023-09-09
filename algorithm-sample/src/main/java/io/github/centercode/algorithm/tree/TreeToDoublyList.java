package io.github.centercode.algorithm.tree;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 */
public class TreeToDoublyList {

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    Node pre, head;

    /**
     * 二叉搜索树的中序遍历为 递增序列
     * 中序遍历完成后，head 指向头节点， pre 指向尾节点，
     */
    public Node solution1(Node root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void dfs(Node cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.left);
        if (pre != null) {
            cur.left = pre;
            pre.right = cur;
        } else {
            head = cur;
        }
        pre = cur;
        dfs(cur.right);
    }
}
