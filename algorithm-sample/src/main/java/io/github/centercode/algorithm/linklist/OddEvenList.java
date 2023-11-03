package io.github.centercode.algorithm.linklist;

/**
 * 328. 奇偶链表
 */
public class OddEvenList {

    public ListNode solution1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode odd = head, even = odd.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
