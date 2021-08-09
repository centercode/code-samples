package io.github.centercode.algorithm.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class SpiralOrderMatrixTest {

    int[][] matrix;

    int[] expect;

    public SpiralOrderMatrixTest(int[][] matrix, int[] expect) {
        this.matrix = matrix;
        this.expect = expect;
    }

    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        int[][] matrix1 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        int[] expected1 = {1, 2, 3, 6, 9, 8, 7, 4, 5};


        int[][] matrix2 = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
        };
        int[] expected2 = {1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7};

        return Arrays.asList(
                new Object[]{matrix1, expected1},
                new Object[]{matrix2, expected2}
        );
    }

    @Test
    public void solution1() {
        SpiralOrderMatrix spiralOrderMatrix = new SpiralOrderMatrix();
        Assert.assertArrayEquals(expect, spiralOrderMatrix.solution1(matrix));
    }
}