package io.github.centercode.algorithm.number;

import io.github.centercode.algorithm.linklist.ListNode;

/**
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 * 1 <= m <= 10^6
 */
public class LastRemaining {
    /**
     * 递归：每当有一个人出圈，下一个人成为新的环头，相当于把数组前移m位。每一轮都重新从0编号
     * f(n, m) = (f(n-1， m) + m) % n
     * tc: O(n), sc: O(n)
     */
    int solution1(int n, int m) {
        if (n == 1) {
            // n为1时剩下的序号为0
            return 0;
        }
        int res = (solution1(n - 1, m) + m) % n;
        System.out.println("n=" + n + ", res=" + res);
        return res;
    }

    /**
     * 同solution1,优化递归
     */
    int solution2(int n, int m) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + m) % i;
        }
        return res;
    }

    /**
     * 链表法
     */
    int solution3(int n, int m) {
        ListNode head = new ListNode(0);
        // 连接链表，共循环n-1次, 循环完成后 pre = tail;
        ListNode pre = head;
        for (int i = 1; i < n; i++) {
            pre.next = new ListNode(i);
            pre = pre.next;
        }
        ListNode tail = pre;
        tail.next = head;

        // 循环删除n个节点，循环完成后只有一个节点，pre == pre.next
        for (int i = 1; i < n; i++) {
            // 后移m-1位，循环完成后current.next指向第m位
            for (int j = 1; j < m; j++) {
                pre = pre.next;
            }
            System.out.println("out:" + pre.next.val);
            pre.next = pre.next.next;
        }
        return pre.val;
    }

}
