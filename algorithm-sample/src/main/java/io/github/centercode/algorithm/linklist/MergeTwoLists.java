package io.github.centercode.algorithm.linklist;

/**
 * 合并两个升序链表
 */
class MergeTwoLists {

    public ListNode solution(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), tail = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = l1 != null ? l1 : l2;

        return head.next;
    }

}