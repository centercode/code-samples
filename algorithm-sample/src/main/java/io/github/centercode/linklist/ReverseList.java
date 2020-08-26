package io.github.centercode.linklist;

import java.util.StringJoiner;

/**
 * 反转链表
 * https://leetcode-cn.com/articles/reverse-linked-list/
 */
public class ReverseList {

    /**
     * 迭代版本
     */
    static ListNode<Integer> solution1(ListNode<Integer> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode<Integer> offset = head.getNext();
        head.setNext(null);
        while (offset != null) {
            ListNode<Integer> nextOffset = offset.getNext();
            //offset替换为头节点
            offset.setNext(head);
            head = offset;
            //游标前移
            offset = nextOffset;
        }

        return head;
    }

    /**
     * 递归版本
     *
     * @return subList head
     */
    static ListNode<Integer> solution2(ListNode<Integer> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode<Integer> subHead = solution2(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return subHead;
    }

    public static class ListNode<T> {

        private T val;

        private ListNode<T> next;

        public ListNode(T val) {
            this.val = val;
        }

        public ListNode<T> getNext() {
            return next;
        }

        public void setNext(ListNode<T> next) {
            this.next = next;
        }

        public static <T> String print(ListNode<T> head) {
            StringJoiner joiner = new StringJoiner(", ");
            while (head != null) {
                joiner.add(head.val == null ? null : head.val.toString());
                head = head.next;
            }
            return joiner.toString();
        }
    }
}
