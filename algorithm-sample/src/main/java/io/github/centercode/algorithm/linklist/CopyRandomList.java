package io.github.centercode.algorithm.linklist;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 35. 复杂链表的复制
 */
public class CopyRandomList {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    Map<Node, Node> cachedNode = new HashMap<>();

    /**
     * 回溯 + 哈希表
     * tc:O(n), sc: O(n)
     */
    public Node solution1(Node head) {
        if (head == null) {
            return null;
        }
        if (!cachedNode.containsKey(head)) {
            Node headNew = new Node(head.val);
            cachedNode.put(head, headNew);
            headNew.next = solution1(head.next);
            headNew.random = solution1(head.random);
        }
        return cachedNode.get(head);
    }

    /**
     * 迭代 + 节点拆分: 对于链表 A→B→C，我们可以将其拆分为 A→A′→B→B′→C→C′。对于任意一个原节点 s，其拷贝节点 S′ 即为其后继节点。
     */
    public Node solution2(Node head) {
        if (head == null) {
            return null;
        }
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = new Node(node.val);
            nodeNew.next = node.next;
            node.next = nodeNew;
        }
        for (Node node = head; node != null; node = node.next.next) {
            Node nodeNew = node.next;
            nodeNew.random = (node.random != null) ? node.random.next : null;
        }
        Node headNew = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return headNew;
    }
}
