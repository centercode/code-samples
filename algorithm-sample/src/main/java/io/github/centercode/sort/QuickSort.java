package io.github.centercode.sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort solution = new QuickSort();
        int[] arr = {1, 2, 33, 14, 4, 28, 9, 5};
        solution.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    void sort(int[] arr, int low, int high) {
        if (low < high) {
            int idx = partition(arr, low, high);
            sort(arr, low, idx - 1);
            sort(arr, idx + 1, high);
        }
    }

    int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && pivot <= arr[high]) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }
}
