package io.github.centercode.algorithm.string;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    public void testCase1Solution1() {
        Assert.assertEquals(2, calculator.case1Solution1("1 + 1"));
        Assert.assertEquals(3, calculator.case1Solution1(" 2-1 + 2 "));
        Assert.assertEquals(23, calculator.case1Solution1("(1+(4+5+2)-3)+(6+8)"));
    }

    @Test
    public void testCase2Solution1() {
        Assert.assertEquals(7, calculator.case2Solution1("3+2*2"));
        Assert.assertEquals(1, calculator.case2Solution1(" 3/2 "));
        Assert.assertEquals(5, calculator.case2Solution1(" 3+5 / 2 "));
    }

    @Test
    public void testCase3Solution1() {
        Assert.assertEquals(2, calculator.case3Solution1("1+1"));
        Assert.assertEquals(4, calculator.case3Solution1("6-4/2"));
        Assert.assertEquals(21, calculator.case3Solution1("2*(5+5*2)/3+(6/2+8)"));
        Assert.assertEquals(21, calculator.case3Solution1("2*(5+5*2)/3+(6/2+8)"));
    }

    @Test
    public void testCase3Solution2() {
        Assert.assertEquals(2, calculator.case3Solution2("1+1"));
        Assert.assertEquals(4, calculator.case3Solution2("6-4/2"));
        Assert.assertEquals(21, calculator.case3Solution2("2*(5+5*2)/3+(6/2+8)"));
        Assert.assertEquals(21, calculator.case3Solution2("2*(5+5*2)/3+(6/2+8)"));
    }

    @Test
    public void testCase3Solution3() {
        Assert.assertEquals(2, calculator.case3Solution3("1+1"));
        Assert.assertEquals(4, calculator.case3Solution3("6-4/2"));
        Assert.assertEquals(21, calculator.case3Solution3("2*(5+5*2)/3+(6/2+8)"));
        Assert.assertEquals(21, calculator.case3Solution3("2*(5+5*2)/3+(6/2+8)"));
    }
}