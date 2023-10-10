package io.github.centercode.algorithm.linklist;

import org.junit.Assert;
import org.junit.Test;

public class HasCycleTest {
    static HasCycle hasCycle = new HasCycle();
    ListNode node0 = new ListNode(0);
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);

    @Test
    public void solution1() {
        Assert.assertEquals(false, hasCycle.solution1(null));
        Assert.assertEquals(false, hasCycle.solution1(new ListNode(0)));
        node0.next = node1;
        node1.next = null;
        Assert.assertEquals(false, hasCycle.solution1(node0));
        node0.next = node1;
        node1.next = node2;
        node2.next = null;
        Assert.assertEquals(false, hasCycle.solution1(node0));
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = null;
        Assert.assertEquals(false, hasCycle.solution1(node0));
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;
        Assert.assertEquals(false, hasCycle.solution1(node0));
        //Cycle
        node0.next = node0;
        Assert.assertEquals(true, hasCycle.solution1(node0));
        node0.next = node1;
        node1.next = node0;
        Assert.assertEquals(true, hasCycle.solution1(node0));
        node0.next = node1;
        node1.next = node2;
        node2.next = node0;
        Assert.assertEquals(true, hasCycle.solution1(node0));
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        Assert.assertEquals(true, hasCycle.solution1(node0));
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node3;
        Assert.assertEquals(true, hasCycle.solution1(node0));
    }
}