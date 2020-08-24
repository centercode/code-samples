package io.github.centercode.sort;

import java.util.Arrays;

/**
 * 堆排序
 */
class HeapSort {

    public static void main(String[] args) {
        HeapSort solution = new HeapSort();
        int[] arr = {1, 2, 33, 14, 4, 28, 9, 5};
        solution.maxHeapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 大顶堆升序排序
     */
    void maxHeapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            siftDown(arr, i, arr.length - 1);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            siftDown(arr, 0, i - 1);
        }
    }

    /**
     * @param arr   数组
     * @param start 排序范围的起始点 include
     * @param end   排序范围的终点 include
     */
    private void siftDown(int[] arr, int start, int end) {
        int dad = start;
        int son = start * 2 + 1;
        int tmp = arr[dad];
        while (son <= end) {
            //找到子节点中最大的
            if (son + 1 <= end && arr[son] < arr[son + 1]) {
                son++;
            }
            if (arr[son] < tmp) {
                break;
            } else {
                arr[dad] = arr[son];
                dad = son;
                son = son * 2 + 1;
            }
        }
        arr[dad] = tmp;
    }

    private void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

}