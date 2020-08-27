package io.github.centercode.string;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class StringToInt2Test {

    private String in;
    private int expect;

    public StringToInt2Test(String in, int expect) {
        this.in = in;
        this.expect = expect;
    }

    @Parameterized.Parameters
    public static Object[] parameters() {
        return new Object[][]{
                new Object[]{"4193 with words", 4193},
                new Object[]{"-91283472332", -2147483648},
                new Object[]{"words and 987", 0},
                new Object[]{" ", 0},
        };
    }

    @Test
    public void solution2() {
        StringToInt stringToInt2 = new StringToInt();
        int val = stringToInt2.solution2(in);
        Assert.assertEquals(expect, val);
    }
}