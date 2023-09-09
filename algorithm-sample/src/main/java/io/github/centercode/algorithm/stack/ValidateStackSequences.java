package io.github.centercode.algorithm.stack;

import java.util.Stack;

/**
 * 剑指 Offer 31. 栈的压入、弹出序列
 */
public class ValidateStackSequences {
    public boolean solution1(int[] pushed, int[] popped) {
        int j = 0;
        Stack<Integer> stack = new Stack<>();
        for (int num : pushed) {
            stack.push(num);
            while (!stack.empty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.empty();
    }
}
