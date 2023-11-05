package io.github.centercode.algorithm.linklist;

import java.util.ArrayDeque;

class GetKthFromEnd {

    /**
     * 输入一个链表，输出该链表中倒数第k个节点。
     * k的序号从1开始
     * 解法一：快慢指针
     */
    public ListNode case1Solution1(ListNode head, int k) {
        ListNode former = head, latter = head;

        for (int i = 0; i < k; i++) {
            former = former.next;
        }

        while (former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }


    /**
     * 解法二：栈
     */
    public ListNode case1Solution2(ListNode head, int k) {
        ArrayDeque<ListNode> stack = new ArrayDeque<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode res = null;
        while (k-- > 0) {
            res = stack.pop();
        }
        return res;
    }

    /**
     * 解法三：两次遍历
     */
    public ListNode case1Solution3(ListNode head, int k) {
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        for (int i = 0; i < len - k; i++) {
            head = head.next;
        }
        return head;
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个值为偶数的节点。
     * k的序号从1开始
     * 解法一：快慢指针
     */
    public ListNode case2Solution1(ListNode head, int k) {
        ListNode latter = null, former = null;
        while (head != null && k > 0) {
            if (head.val % 2 == 0) {
                k--;
                ListNode headCopy = new ListNode(head.val);
                if (latter == null) {
                    latter = headCopy;
                    former = headCopy;
                } else {
                    former.next = headCopy;
                }
            }
            head = head.next;
        }
        while (head != null) {
            if (head.val % 2 == 0) {
                former.next = new ListNode(head.val);
                former = former.next;
                latter = latter.next;
            }
            head = head.next;
        }
        return latter;
    }

}