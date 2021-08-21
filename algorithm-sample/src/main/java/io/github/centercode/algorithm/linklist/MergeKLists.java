package io.github.centercode.algorithm.linklist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并k个升序链表
 */
public class MergeKLists {

    MergeTwoLists mergeTwoLists = new MergeTwoLists();

    /**
     * 顺序合并，时间复杂度为 O(k^2 * n)，空间复杂度为 O(1)
     */
    public ListNode solution1(ListNode[] lists) {
        ListNode ans = null;
        for (ListNode list : lists) {
            ans = mergeTwoLists.solution(ans, list);
        }
        return ans;
    }

    /**
     * 分治合并，时间复杂度为 O(kn×logk)，空间复杂度O(logk)
     */
    public ListNode solution2(ListNode[] lists) {
        return binMerge(lists, 0, lists.length - 1);
    }

    private ListNode binMerge(ListNode[] lists, int i, int j) {
        if (i == j) {
            return lists[i];
        }
        if (i > j) {
            return null;
        }
        int m = (i + j) / 2;
        ListNode left = binMerge(lists, i, m);
        ListNode right = binMerge(lists, m + 1, j);
        return mergeTwoLists.solution(left, right);
    }

    /**
     * 使用优先队列合并，时间复杂度为O(kn×logk)，空间复杂度为O(k)
     */
    public ListNode solution3(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.offer(node);
            }
        }
        ListNode head = new ListNode(0), tail = head;
        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                priorityQueue.offer(node.next);
            }
        }
        return head.next;
    }
}
