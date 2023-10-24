package io.github.centercode.algorithm.linklist;

import org.junit.Assert;
import org.junit.Test;

public class ReverseListTest {

    ReverseList reverseList = new ReverseList();

    @Test
    public void case1Solution1() {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(2);
        n1.next.next = new ListNode(3);
        Assert.assertEquals("[3,2,1]", reverseList.case1Solution1(n1).toString());
    }

    @Test
    public void case2Solution1() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        Assert.assertEquals("[2,1,4,3,5]", reverseList.case2Solution1(head, 2).toString());

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        Assert.assertEquals("[3,2,1,4,5]", reverseList.case2Solution1(head, 3).toString());
    }
}