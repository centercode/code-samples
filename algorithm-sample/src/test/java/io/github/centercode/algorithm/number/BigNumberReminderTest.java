package io.github.centercode.algorithm.number;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BigNumberReminderTest {

    BigNumberReminder bigNumberReminder = new BigNumberReminder();

    @Test
    public void solution1() {
        int x = 1001;
        int a = 1;
        int p = 199;
        int r = 6;
        Assert.assertEquals(r, bigNumberReminder.solution1(x, a, p));
        Assert.assertEquals(r, bigNumberReminder.solution2(x, a, p));
    }
}