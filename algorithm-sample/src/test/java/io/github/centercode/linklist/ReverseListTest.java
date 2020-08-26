package io.github.centercode.linklist;

import org.junit.Test;

public class ReverseListTest {

    @Test
    public void solution1() {
        ReverseList.ListNode<Integer> n1 = new ReverseList.ListNode<>(1);
        ReverseList.ListNode<Integer> n2 = new ReverseList.ListNode<>(2);
        ReverseList.ListNode<Integer> n3 = new ReverseList.ListNode<>(3);
        n1.setNext(n2);
        n2.setNext(n3);
        System.out.println("Input:" + ReverseList.ListNode.print(n1));

        ReverseList.ListNode<Integer> head = ReverseList.solution2(n1);
        System.out.println("Output:" + ReverseList.ListNode.print(head));
    }
}