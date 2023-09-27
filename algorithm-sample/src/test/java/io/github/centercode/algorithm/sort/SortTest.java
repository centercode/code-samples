package io.github.centercode.algorithm.sort;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class SortTest {

    private final int[] data;

    private final int[] expect;

    public SortTest(int[] data, int[] expect) {
        this.data = data;
        this.expect = expect;
    }

    @Parameterized.Parameters
    public static Object[] data() {
        return new Object[][] {
            new Object[] {new int[] {}, new int[] {}},
            new Object[] {new int[] {1}, new int[] {1}},
            new Object[] {new int[] {1, 2, 3}, new int[] {1, 2, 3}},
            new Object[] {new int[] {3, 2, 1}, new int[] {1, 2, 3}},
            new Object[] {new int[] {5, 4, 3, 2, 1}, new int[] {1, 2, 3, 4, 5}},
            new Object[] {new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 4, 5}},
            new Object[] {new int[] {1, 1, 1, 1, 1}, new int[] {1, 1, 1, 1, 1}},
            new Object[] {new int[] {1, 2, 33, 5, 14, 4, 28, 9, 5}, new int[] {1, 2, 4, 5, 5, 9, 14, 28, 33}},
        };
    }

    @Test
    public void testSelectionSort() {
        SelectionSort sorter = new SelectionSort();
        int[] copy = Arrays.copyOf(data, data.length);
        sorter.sort(copy);
        Assert.assertArrayEquals(expect, copy);
    }

    @Test
    public void testBubbleSort() {
        BubbleSort sorter = new BubbleSort();
        int[] copy = Arrays.copyOf(data, data.length);
        sorter.sort(copy);
        Assert.assertArrayEquals(expect, copy);
    }

    @Test
    public void testInsertSort() {
        InsertSort sorter = new InsertSort();
        int[] copy = Arrays.copyOf(data, data.length);
        sorter.sort(copy);
        Assert.assertArrayEquals(expect, copy);
    }

    @Test
    public void testQuickSort() {
        QuickSort sorter = new QuickSort();
        int[] copy = Arrays.copyOf(data, data.length);
        sorter.sort(copy);
        Assert.assertArrayEquals(expect, copy);
    }
}