package io.github.centercode.algorithm.stack;

import org.junit.Assert;
import org.junit.Test;

public class MinStackTest {

    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-1);
        Assert.assertEquals(-2, minStack.min());
        Assert.assertEquals(-1, minStack.top());
        minStack.pop();
        Assert.assertEquals(-2, minStack.min());
    }
}