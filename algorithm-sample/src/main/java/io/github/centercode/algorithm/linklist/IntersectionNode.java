package io.github.centercode.algorithm.linklist;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 */
public class IntersectionNode {

    /**
     * 双指针
     */
    public ListNode solution1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
    }
}