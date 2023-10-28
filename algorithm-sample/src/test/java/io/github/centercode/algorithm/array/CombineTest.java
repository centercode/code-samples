package io.github.centercode.algorithm.array;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CombineTest {
    Combine combine = new Combine();

    @Test
    public void solution1() {
        List<List<Integer>> res = combine.solution1(4, 2);
        assertEquals(6, res.size());
        assertTrue(res.contains(Arrays.asList(2, 1)));
        assertTrue(res.contains(Arrays.asList(3, 1)));
        assertTrue(res.contains(Arrays.asList(3, 2)));
        assertTrue(res.contains(Arrays.asList(4, 1)));
        assertTrue(res.contains(Arrays.asList(4, 2)));
        assertTrue(res.contains(Arrays.asList(4, 3)));

        res = combine.solution1(1, 1);
        assertEquals(1, res.size());
        assertTrue(res.contains(Arrays.asList(1)));
    }
}