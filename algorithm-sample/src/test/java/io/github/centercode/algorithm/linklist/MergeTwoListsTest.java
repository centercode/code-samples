package io.github.centercode.algorithm.linklist;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MergeTwoListsTest {

    @Test
    public void solutionTest() {
        ListNode l1v1 = new ListNode(1);
        ListNode l1v2 = new ListNode(2);
        ListNode l1v4 = new ListNode(4);

        l1v1.next = l1v2;
        l1v2.next = l1v4;

        ListNode l2v1 = new ListNode(1);
        ListNode l2v3 = new ListNode(3);
        ListNode l2v4 = new ListNode(4);

        l2v1.next = l2v3;
        l2v3.next = l2v4;

        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        ListNode mergedList = mergeTwoLists.solution(l1v1, l2v1);

        ArrayList<Integer> actual = new ArrayList<>();
        while (mergedList != null) {
            actual.add(mergedList.val);
            mergedList = mergedList.next;
        }

        ArrayList<Integer> expect = new ArrayList<>();
        expect.add(1);
        expect.add(1);
        expect.add(2);
        expect.add(3);
        expect.add(4);
        expect.add(4);
        Assert.assertEquals(expect, actual);

    }

}