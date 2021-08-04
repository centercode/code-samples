package io.github.centercode.algorithm.linklist;

import org.junit.Test;

import java.util.Arrays;

public class ReversePrintListTest {

    @Test
    public void solution1() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(2);

        n1.next = n2;
        n2.next = n3;

        ReversePrintList reversePrintList = new ReversePrintList();
        int[] arr = reversePrintList.solution1(n1);
        System.out.println(Arrays.toString(arr));
    }
}