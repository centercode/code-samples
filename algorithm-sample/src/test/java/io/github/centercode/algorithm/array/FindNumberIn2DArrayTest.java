package io.github.centercode.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

public class FindNumberIn2DArrayTest {

    @Test
    public void findNumberIn2DArray() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        FindNumberIn2DArray findNumberIn2DArray = new FindNumberIn2DArray();
        boolean found = findNumberIn2DArray.solution1(matrix, 5);
        Assert.assertTrue(found);
        found = findNumberIn2DArray.solution1(matrix, 20);
        Assert.assertFalse(found);
    }
}