package io.github.centercode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 括号匹配
 */
class QuoteMatch {

    public static void main(String[] args) {
        QuoteMatch solution = new QuoteMatch();
        boolean valid = solution.isValid("{}[]()");
        System.out.println(valid);
    }

    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || c != stack.pop()) return false;
        }
        return stack.isEmpty();
    }
}