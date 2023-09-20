package io.github.centercode.algorithm.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinNumberTest {
    MinNumber minNumber = new MinNumber();

    @Test
    public void solution1() {
        assertEquals("102", minNumber.solution1(new int[] {10, 2}));
        assertEquals("3033459", minNumber.solution1(new int[] {3, 30, 34, 5, 9}));
    }
}