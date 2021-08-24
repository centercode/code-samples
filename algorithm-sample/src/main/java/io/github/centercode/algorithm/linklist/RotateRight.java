package io.github.centercode.algorithm.linklist;

/**
 * 给你一个链表的头节点 head ，将链表每个节点向右移动 k 个位置。
 */
public class RotateRight {

    public ListNode solution1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int n = 1;
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
            n++;
        }
        node.next = head;
        k = n - k % n;
        if (k == n) {
            return head;
        }
        while (k-- > 0) {
            node = node.next;
        }
        ListNode ret = node.next;
        node.next = null;
        return ret;
    }
}