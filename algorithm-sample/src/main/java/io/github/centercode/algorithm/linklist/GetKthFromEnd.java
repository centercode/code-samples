package io.github.centercode.algorithm.linklist;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。
 * k的序号从1开始
 */
class GetKthFromEnd {

    /**
     * 快慢指针
     */
    public ListNode solution1(ListNode head, int k) {
        ListNode former = head, latter = head;

        for (int i = 0; i < k - 1; i++) {
            former = former.next;
        }

        while (former.next != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }

    /**
     * 栈，递归
     */
    // public ListNode solution1(ListNode head, int k) {}
}