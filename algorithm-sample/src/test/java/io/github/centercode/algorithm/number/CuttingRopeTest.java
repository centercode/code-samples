package io.github.centercode.algorithm.number;

import org.junit.Assert;
import org.junit.Test;

public class CuttingRopeTest {
    CuttingRope cuttingRope = new CuttingRope();

    @Test
    public void cuttingRope() {
        Assert.assertEquals(1, cuttingRope.case1Solution1(2));
        Assert.assertEquals(36, cuttingRope.case1Solution1(10));
    }

    @Test
    public void case2Solution1() {
       Assert.assertEquals(36,  cuttingRope.case2Solution1(10));
    }
}