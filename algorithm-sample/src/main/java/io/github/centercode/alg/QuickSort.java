package io.github.centercode.alg;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort solution = new QuickSort();
        int[] arr = {1, 2, 33, 14, 4, 28, 9, 5};
        solution.qSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    void qSort(int[] arr) {
        qSort(arr, 0, arr.length - 1);
    }

    void qSort(int[] arr, int low, int high) {
        if (low < high) {
            int idx = partition(arr, low, high);
            qSort(arr, low, idx - 1);
            qSort(arr, idx + 1, high);
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
