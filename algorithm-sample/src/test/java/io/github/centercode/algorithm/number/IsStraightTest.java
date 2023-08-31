package io.github.centercode.algorithm.number;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class IsStraightTest {

    @Test
    public void solution2() {
        IsStraight isStraight = new IsStraight();
        Assert.assertTrue(isStraight.solution2(new int[]{0, 0, 0, 0, 0}));
    }
}