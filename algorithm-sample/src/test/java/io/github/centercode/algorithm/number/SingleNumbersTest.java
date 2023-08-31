package io.github.centercode.algorithm.number;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SingleNumbersTest {

    @Test
    public void solution1() {
        SingleNumbers singleNumbers = new SingleNumbers();
        int[] results = singleNumbers.solution1(new int[]{4, 1, 4, 6});
        Arrays.sort(results);
        Assert.assertArrayEquals(new int[]{1, 6}, results);

        results = singleNumbers.solution1(new int[]{1, 2, 10, 4, 1, 4, 3, 3});
        Arrays.sort(results);
        Assert.assertArrayEquals(new int[]{2, 10}, results);
    }
}