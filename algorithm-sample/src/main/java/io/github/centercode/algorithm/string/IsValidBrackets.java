package io.github.centercode.algorithm.string;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 20. 有效的括号：
 * 给定一个只包括'('，')'，'{'，'}'，'['，']'的字符串s，判断字符串是否有效。
 */
public class IsValidBrackets {

    public boolean solution1(String s) {
        if (s == null) {
            return true;
        }
        Deque<Character> deque = new LinkedList<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '{':
                case '[':
                    deque.offerLast(c);
                    break;
                case ')':
                    if (!deque.isEmpty() && deque.peekLast().equals('(')) {
                        deque.pollLast();
                        break;
                    } else {
                        return false;
                    }
                case '}':
                    if (!deque.isEmpty() && deque.peekLast().equals('{')) {
                        deque.pollLast();
                        break;
                    } else {
                        return false;
                    }
                case ']':
                    if (!deque.isEmpty() && deque.peekLast().equals('[')) {
                        deque.pollLast();
                        break;
                    } else {
                        return false;
                    }
                default:
                    throw new IllegalStateException();
            }
        }
        return deque.isEmpty();
    }
}
