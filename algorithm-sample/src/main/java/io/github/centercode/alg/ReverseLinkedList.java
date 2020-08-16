package io.github.centercode.alg;

import java.util.StringJoiner;

/**
 * 反转链表
 * https://leetcode-cn.com/articles/reverse-linked-list/
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        LinkedNode<Integer> n3 = new LinkedNode<>(3, null);
        LinkedNode<Integer> n2 = new LinkedNode<>(2, n3);
        LinkedNode<Integer> n1 = new LinkedNode<>(1, n2);
        System.out.println("Input:" + LinkedNode.print(n1));

        LinkedNode<Integer> head = reverse2(n1);
        System.out.println("Input:" + LinkedNode.print(head));
    }

    /**
     * 迭代版本
     */
    static LinkedNode<Integer> reverse1(LinkedNode<Integer> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        LinkedNode<Integer> offset = head.getNext();
        head.setNext(null);
        while (offset != null) {
            LinkedNode<Integer> nextOffset = offset.getNext();
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
    static LinkedNode<Integer> reverse2(LinkedNode<Integer> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        LinkedNode<Integer> subHead = reverse2(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return subHead;
    }

    public static class LinkedNode<T> {

        private T Data;

        private LinkedNode<T> next;

        public LinkedNode(T data, LinkedNode<T> next) {
            Data = data;
            this.next = next;
        }

        public T getData() {
            return Data;
        }

        public void setData(T data) {
            Data = data;
        }

        public LinkedNode<T> getNext() {
            return next;
        }

        public void setNext(LinkedNode<T> next) {
            this.next = next;
        }

        public static <T> String print(LinkedNode<T> head) {
            StringJoiner joiner = new StringJoiner(", ");
            while (head != null) {
                joiner.add(head.Data == null ? null : head.Data.toString());
                head = head.next;
            }
            return joiner.toString();
        }
    }
}
