package io.github.centercode.algorithm.tree;

import java.util.Collections;
import java.util.LinkedList;

public class Codec {

    /**
     * 先序遍历
     */
    static class Solution1 {

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        public void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("null,");
                return;
            }
            sb.append(root.val).append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }

        public TreeNode deserialize(String data) {
            String[] parts = data.split(",");
            LinkedList<String> list = new LinkedList<>();
            Collections.addAll(list, parts);
            return deserialize(list);
        }

        public TreeNode deserialize(LinkedList<String> list) {
            String head = list.get(0);
            list.remove(0);
            if (head.equals("null")) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(head));
            root.left = deserialize(list);
            root.right = deserialize(list);
            return root;
        }
    }

    static class Solution2 {

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serialize(root, sb);
            return sb.toString();
        }

        public void serialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("X");
            } else {
                sb.append("(").append(serialize(root.left)).append(")");
                sb.append(root.val);
                sb.append("(").append(serialize(root.right)).append(")");
            }
        }

        public TreeNode deserialize(String data) {
            int[] ptr = {0};
            return deserialize(data, ptr);
        }

        public TreeNode deserialize(String data, int[] ptr) {
            char c = data.charAt(ptr[0]);
            if (c == 'X') {
                ++ptr[0];
                return null;
            }
            TreeNode node = new TreeNode(-1);
            node.left = parseSubtree(data, ptr);
            node.val = parseInt(data, ptr);
            node.right = parseSubtree(data, ptr);
            return node;
        }

        public TreeNode parseSubtree(String data, int[] ptr) {
            ++ptr[0]; // 跳过左括号
            TreeNode subtree = deserialize(data, ptr);
            ++ptr[0]; // 跳过右括号
            return subtree;
        }

        private int parseInt(String data, int[] ptr) {
            int r = 0, sign = 1;
            if (data.charAt(ptr[0]) == '-') {
                sign = -1;
                ptr[0]++;
            }
            while (Character.isDigit(data.charAt(ptr[0]))) {
                r = r * 10 + (data.charAt(ptr[0]++) - '0');
            }
            return r * sign;
        }
    }
}
