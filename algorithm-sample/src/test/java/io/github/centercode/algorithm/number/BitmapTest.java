package io.github.centercode.algorithm.number;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitmapTest {

    @Test
    public void set() {
        int[] val = new int[3];
        assertFalse(Bitmap.test(val, 3));
        Bitmap.set(val, 3);
        assertTrue(Bitmap.test(val, 3));
    }
}