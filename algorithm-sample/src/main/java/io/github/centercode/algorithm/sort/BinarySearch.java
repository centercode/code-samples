package io.github.centercode.algorithm.sort;

/**
 * @see java.util.Arrays
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 1, 2};
        BinarySearch binarySearch = new BinarySearch();
        int i = binarySearch.search(arr, 0);
        System.out.println(i);
    }

    int search(int[] arr, int key) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int middle = (low + high) >>> 1;
            if (arr[middle] < key) {
                low = middle + 1;
            } else if (key < arr[middle]) {
                high = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
