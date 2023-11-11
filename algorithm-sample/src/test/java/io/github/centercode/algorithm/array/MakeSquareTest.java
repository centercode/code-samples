package io.github.centercode.algorithm.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class MakeSquareTest {
    MakeSquare makeSquare = new MakeSquare();

    @Test
    public void solution1() {
        int[] data = {1, 1, 2, 2, 2};
        assertTrue(makeSquare.solution1(data));
        data = new int[] {3, 3, 3, 3, 4};
        assertFalse(makeSquare.solution1(data));
        data = new int[] {8, 16, 24, 32, 40, 48, 56, 64, 72, 80, 88, 96, 104, 112, 60};
        assertFalse(makeSquare.solution1(data));
    }

    @Test
    public void solution2() {
        int[] data = {1, 1, 2, 2, 2};
        assertTrue(makeSquare.solution2(data));
        data = new int[] {3, 3, 3, 3, 4};
        assertFalse(makeSquare.solution2(data));
        data = new int[] {8, 16, 24, 32, 40, 48, 56, 64, 72, 80, 88, 96, 104, 112, 60};
        assertFalse(makeSquare.solution2(data));
    }
}