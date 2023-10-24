package io.github.centercode.algorithm.linklist;

import java.util.StringJoiner;

public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        ListNode head = this;
        while (head != null) {
            joiner.add(String.valueOf(head.val));
            head = head.next;
        }
        return joiner.toString();
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                return head;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
