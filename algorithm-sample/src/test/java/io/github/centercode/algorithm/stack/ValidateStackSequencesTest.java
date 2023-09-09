package io.github.centercode.algorithm.stack;

import org.junit.Assert;
import org.junit.Test;

public class ValidateStackSequencesTest {
    ValidateStackSequences validateStackSequences = new ValidateStackSequences();

    @Test
    public void validateStackSequences() {
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 5, 3, 2, 1};
        Assert.assertTrue(validateStackSequences.solution1(pushed, popped));
        popped = new int[]{4, 3, 5, 1, 2};
        Assert.assertFalse(validateStackSequences.solution1(pushed, popped));
    }
}