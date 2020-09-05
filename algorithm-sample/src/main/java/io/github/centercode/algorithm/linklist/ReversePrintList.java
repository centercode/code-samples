package io.github.centercode.algorithm.linklist;

public class ReversePrintList {

    public int[] solution1(ListNode head) {
        int n = 0;
        ListNode c = head;
        while (c != null) {
            n++;
            c = c.next;
        }
        int[] arr = new int[n];

        while (n > 0) {
            arr[n - 1] = head.val;
            n--;
            head = head.next;

        }
        return arr;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
