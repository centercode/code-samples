package io.github.centercode.algorithm.array;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class PeakIndexInMountainArrayTest {
    PeakIndexInMountainArray peakIndexInMountainArray = new PeakIndexInMountainArray();

    @Test
    public void solution1() {
        int[] data = {0, 1, 0};
        Assert.assertEquals(1, peakIndexInMountainArray.solution1(data));
        data = new int[] {0, 2, 1, 0};
        Assert.assertEquals(1, peakIndexInMountainArray.solution1(data));
        data = new int[] {0, 10, 5, 2};
        Assert.assertEquals(1, peakIndexInMountainArray.solution1(data));
    }
}