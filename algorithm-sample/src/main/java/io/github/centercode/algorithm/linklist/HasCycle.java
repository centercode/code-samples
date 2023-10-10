package io.github.centercode.algorithm.linklist;

/**
 * 141. 环形链表
 */
public class HasCycle {

    /**
     * 快慢指针
     */
    public boolean solution1(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
