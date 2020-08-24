package io.github.centercode.alg;

/**
 * 数组是不是二叉搜索树的后序遍历
 * 数组的任意两个数字都互不相同
 * 5,7,6,9,11,10,8 true
 * 7,4,6,5 false
 */
public class SearchTree {

    public static void main(String[] args) {
        int[] arr = {7, 4, 6, 5};
        SearchTree searchTree = new SearchTree();
        boolean lrdOfBinSearchTree = searchTree.isLRD(arr);
        System.out.println(lrdOfBinSearchTree);
    }

    boolean isLRD(int[] arr) {
        if (arr == null) {
            return true;
        }
        return isLRD(arr, 0, arr.length - 1);
    }

    boolean isLRD(int[] arr, int low, int high) {
        if (low == high) {
            return true;
        }
        int i = low;
        while (i < high && arr[i] < arr[high]) {
            i++;
        }
        while (i < high && arr[i] > arr[high]) {
            i++;
        }
        if (arr[i] < arr[high]) {
            return false;
        }
        boolean isLeftMatch = isLRD(arr, low, i - 1);
        return isLeftMatch && isLRD(arr, i, high);
    }
}
