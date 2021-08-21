package io.github.centercode.algorithm.stack;


import java.util.Stack;

/**
 * No.30 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)
 */
class MinStack {

    Stack<Integer> A;

    Stack<Integer> B;

    public MinStack() {
        A = new Stack<>();
        B = new Stack<>();
    }

    void push(int x) {
        if (B.empty() || x <= B.peek()) {
            B.push(x);
        }
        A.push(x);
    }

    void pop() {
        if (B.peek().equals(A.peek())) {
            B.pop();
        }
        A.pop();
    }

    int top() {
        return A.peek();
    }

    int min() {
        return B.peek();
    }
}
