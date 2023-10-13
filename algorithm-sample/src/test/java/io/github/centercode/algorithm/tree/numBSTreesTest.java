package io.github.centercode.algorithm.tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class numBSTreesTest {

    @Test
    public void solution1() {
        NumBSTrees numBSTrees = new NumBSTrees();
        assertEquals(5, numBSTrees.solution1(3));
        assertEquals(1, numBSTrees.solution1(1));
    }
}