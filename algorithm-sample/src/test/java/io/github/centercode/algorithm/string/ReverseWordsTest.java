package io.github.centercode.algorithm.string;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseWordsTest {

    @Test
    public void solutions() {
        ReverseWords algo = new ReverseWords();
        assertEquals("student. a am I", algo.solution1("I am a student."));
    }
}