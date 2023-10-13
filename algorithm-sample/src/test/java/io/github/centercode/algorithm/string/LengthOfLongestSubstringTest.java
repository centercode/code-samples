package io.github.centercode.algorithm.string;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class LengthOfLongestSubstringTest {

    LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();

    String s;

    int len;

    public LengthOfLongestSubstringTest(String s, int len) {
        this.s = s;
        this.len = len;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
            new Object[] {"abba", 2},
            new Object[] {"abcabcbb", 3},
            new Object[] {"bbbbb", 1},
            new Object[] {"pwwkew", 3},
        };
    }

    @Test
    public void solution1() {
        assertEquals(len, lengthOfLongestSubstring.solution1(s));
    }

    @Test
    public void solution2() {
        assertEquals(len, lengthOfLongestSubstring.solution2(s));
    }
}