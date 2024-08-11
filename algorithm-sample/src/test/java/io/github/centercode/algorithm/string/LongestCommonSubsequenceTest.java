package io.github.centercode.algorithm.string;

import org.junit.Assert;
import org.junit.Test;

public class LongestCommonSubsequenceTest {

    @Test
    public void solution1() {
        LongestCommonSubsequence subsequence = new LongestCommonSubsequence();
        Assert.assertEquals(3, subsequence.solution1("abcde", "ace"));
        Assert.assertEquals(3, subsequence.solution1("abc", "abc"));
    }

    @Test
    public void solution2() {
        LongestCommonSubsequence subsequence = new LongestCommonSubsequence();
        Assert.assertEquals(3, subsequence.solution2("abcde", "ace"));
        Assert.assertEquals(3, subsequence.solution2("abc", "abc"));
        Assert.assertEquals(1, subsequence.solution2("bsbininm", "jmjkbkjkv"));
    }
}