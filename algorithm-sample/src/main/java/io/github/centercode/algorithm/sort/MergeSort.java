package io.github.centercode.algorithm.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        MergeSort solution = new MergeSort();
        int[] arr = {1, 2, 33, 14, 4, 28, 9, 5};
        solution.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    void sort(int[] arr) {
        int[] tmp = new int[arr.length];
        sort(arr, 0, arr.length - 1, tmp);
    }

    void sort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int m = (left + right) / 2;
            sort(arr, left, m, tmp);
            sort(arr, m + 1, right, tmp);
            merge(arr, left, m, right, tmp);
        }
    }

    void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int t = 0, x = left, y = mid + 1;
        while (x <= mid && y <= right) {
            if (arr[x] <= arr[y]) {
                tmp[t++] = arr[x++];
            } else {
                tmp[t++] = arr[y++];
            }
        }
        while (x <= mid) {
            tmp[t++] = arr[x++];
        }
        while (y <= right) {
            tmp[t++] = arr[y++];
        }
        t = 0;
        while (left <= right) {
            arr[left++] = tmp[t++];
        }
    }
}
