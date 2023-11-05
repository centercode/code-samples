package io.github.centercode.algorithm.linklist;

import org.junit.Assert;
import org.junit.Test;

public class GetKthFromEndTest {

    GetKthFromEnd getKthFromEnd = new GetKthFromEnd();

    ListNode head = new ListNode(1);

    {
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
    }

    @Test
    public void case1Solution1() {
        Assert.assertEquals(1, getKthFromEnd.case1Solution1(head, 5).val);
        Assert.assertEquals(4, getKthFromEnd.case1Solution1(head, 2).val);
        Assert.assertEquals(5, getKthFromEnd.case1Solution1(head, 1).val);
        Assert.assertEquals(5, getKthFromEnd.case1Solution1(head.next.next.next.next, 1).val);
    }

    @Test
    public void case1Solution2() {
        Assert.assertEquals(1, getKthFromEnd.case1Solution2(head, 5).val);
        Assert.assertEquals(4, getKthFromEnd.case1Solution2(head, 2).val);
        Assert.assertEquals(5, getKthFromEnd.case1Solution2(head, 1).val);
        Assert.assertEquals(5, getKthFromEnd.case1Solution2(head.next.next.next.next, 1).val);
    }

    @Test
    public void case1Solution3() {
        Assert.assertEquals(1, getKthFromEnd.case1Solution3(head, 5).val);
        Assert.assertEquals(4, getKthFromEnd.case1Solution3(head, 2).val);
        Assert.assertEquals(5, getKthFromEnd.case1Solution3(head, 1).val);
        Assert.assertEquals(5, getKthFromEnd.case1Solution3(head.next.next.next.next, 1).val);
    }

    @Test
    public void case2Solution1() {
        Assert.assertEquals(4, getKthFromEnd.case2Solution1(head, 1).val);
        Assert.assertEquals(2, getKthFromEnd.case2Solution1(head, 2).val);
        Assert.assertEquals(4, getKthFromEnd.case2Solution1(head.next, 1).val);
        Assert.assertEquals(2, getKthFromEnd.case2Solution1(head.next, 2).val);
    }
}