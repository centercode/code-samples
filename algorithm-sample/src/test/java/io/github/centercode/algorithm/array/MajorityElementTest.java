package io.github.centercode.algorithm.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MajorityElementTest {

    int nums[];
    int majority;

    public MajorityElementTest(int[] nums, int majority) {
        this.nums = nums;
        this.majority = majority;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                new Object[]{new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}, 2}
        });
    }

    @Test
    public void solution1() {
        MajorityElement majorityElement = new MajorityElement();
        Assert.assertEquals(majority, majorityElement.solution1(nums));
    }
}