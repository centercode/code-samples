package io.github.centercode.algorithm.number;

import org.junit.Assert;
import org.junit.Test;

public class FindContinuousSequenceTest {

    @Test
    public void solution1() {
        int[][] expectResult = {
                {1, 2, 3, 4, 5},
                {4, 5, 6},
                {7, 8}
        };
        FindContinuousSequence findContinuousSequence = new FindContinuousSequence();
        int[][] result = findContinuousSequence.solution2(15);
        Assert.assertArrayEquals(result, expectResult);
    }
}