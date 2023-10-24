package io.github.centercode.algorithm.linklist;

/**
 * 反转链表
 */
public class ReverseList {

    /**
     * 206. 反转链表
     * 解法一：迭代版本
     */
    ListNode case1Solution1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 解法二：递归版本
     *
     * @return 子链表反转后的头节点
     */
    ListNode case1Solution2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode subHead = case1Solution2(head.next);
        head.next.next = head;
        head.next = null;
        return subHead;
    }

    /**
     * 25. K个一组翻转链表：
     * 给你链表的头节点head，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * k是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
     * <p>
     * 解法一：链表分区为已翻转部分+待翻转部分+未翻转部分
     */
    ListNode case2Solution1(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // pre是已翻转部分的最后一个节点
        ListNode preEnd = dummy;
        // end是待翻转部分的最后一个节点
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            // start是待翻转部分首节点
            ListNode start = preEnd.next;
            // next是未翻转部分首节点
            ListNode next = end.next;
            end.next = null;
            // reverse
            preEnd.next = case1Solution1(start);
            // 此时start是原待翻转部分的尾节点
            start.next = next;
            preEnd = start;
            end = start;
        }
        return dummy.next;
    }

}
