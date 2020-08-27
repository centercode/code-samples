package io.github.centercode.string;

import org.junit.Assert;
import org.junit.Test;

public class SimpleStringToIntTest {

    @Test
    public void solution1() {
        String s = "+123";
        SimpleStringToInt stringToInt = new SimpleStringToInt();
        int r1 = stringToInt.solution1(s);
        int r2 = stringToInt.solution2(s);
        System.out.println(r1);
        System.out.println(r2);
        Assert.assertEquals(r1, r2);
    }
}