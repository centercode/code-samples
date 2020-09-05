package io.github.centercode.algorithm.linklist;

import org.junit.Test;

import java.util.Arrays;

public class ReversePrintListTest {

    @Test
    public void solution1() {
        ReversePrintList.ListNode n1 = new ReversePrintList.ListNode(1);
        ReversePrintList.ListNode n2 = new ReversePrintList.ListNode(3);
        ReversePrintList.ListNode n3 = new ReversePrintList.ListNode(2);

        n1.next = n2;
        n2.next = n3;

        ReversePrintList reversePrintList = new ReversePrintList();
        int[] arr = reversePrintList.solution1(n1);
        System.out.println(Arrays.toString(arr));
    }
}