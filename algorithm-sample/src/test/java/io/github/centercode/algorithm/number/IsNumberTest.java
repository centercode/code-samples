package io.github.centercode.algorithm.number;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class IsNumberTest {
    IsNumber isNumber = new IsNumber();

    @Test
    public void solution1() {
        Assert.assertTrue(isNumber.solution1("0"));
        Assert.assertTrue(isNumber.solution1("   .1"));
        Assert.assertTrue(isNumber.solution1("   1."));
        Assert.assertTrue(isNumber.solution1("   1.0"));
        Assert.assertTrue(isNumber.solution1("-1.0   "));
        Assert.assertTrue(isNumber.solution1(" +100 "));
        Assert.assertTrue(isNumber.solution1("5e2"));
        Assert.assertTrue(isNumber.solution1("-123 "));
        Assert.assertTrue(isNumber.solution1(" 3.1415 "));
        Assert.assertTrue(isNumber.solution1(" -1E-16 "));
        Assert.assertTrue(isNumber.solution1("0123 "));
        Assert.assertFalse(isNumber.solution1("e"));
        Assert.assertFalse(isNumber.solution1("."));
    }
}