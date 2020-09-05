package io.github.centercode.algorithm.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class FindRepeatNumberTest {

    int[] nums;

    List<Integer> validResults;

    public FindRepeatNumberTest(int[] nums, List<Integer> validResults) {
        this.nums = nums;
        this.validResults = validResults;
    }

    @Parameterized.Parameters
    public static Object[] primeNumbers() {
        return new Object[][]{
                {new int[]{2, 3, 1, 0, 2, 5, 3}, Arrays.asList(2, 3)},
        };
    }

    @Test
    public void findRepeatNumber() {
        FindRepeatNumber solution = new FindRepeatNumber();
        int repeatNumber = solution.findRepeatNumber(nums);
        Assert.assertTrue(validResults.contains(repeatNumber));
    }
}