package io.github.centercode.algorithm.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EvalRPNTest {

    EvalRPN evalRPN = new EvalRPN();

    @Test
    public void solution1() {
        String[] data = {"2", "1", "+", "3", "*"};
        assertEquals(9, evalRPN.solution1(data));
        data = new String[] {"4", "13", "5", "/", "+"};
        assertEquals(6, evalRPN.solution1(data));
        data = new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        assertEquals(22, evalRPN.solution1(data));
    }

    @Test
    public void solution2() {
        String[] data = {"2", "1", "+", "3", "*"};
        assertEquals(9, evalRPN.solution2(data));
        data = new String[] {"4", "13", "5", "/", "+"};
        assertEquals(6, evalRPN.solution2(data));
        data = new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        assertEquals(22, evalRPN.solution2(data));
    }
}