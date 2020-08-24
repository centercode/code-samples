package io.github.centercode.tree;

/**
 * 数组是不是二叉搜索树的后序遍历
 * 数组的任意两个数字都互不相同
 * 5,7,6,9,11,10,8 true
 * 7,4,6,5 false
 */
public class BinSearchTree {

    public static void main(String[] args) {
        int[] arr = {7, 4, 6, 5};
        BinSearchTree searchTree = new BinSearchTree();
        boolean lrdOfBinSearchTree = searchTree.isLRD(arr);
        System.out.println(lrdOfBinSearchTree);
    }

    boolean isLRD(int[] arr) {
        if (arr == null) {
            return true;
        }
        return isLRD(arr, 0, arr.length - 1);
    }

    boolean isLRD(int[] arr, int start, int end) {
        if (start == end) {
            return true;
        }
        int i = start;
        while (i < end && arr[i] < arr[end]) {
            i++;
        }
        while (i < end && arr[i] > arr[end]) {
            i++;
        }
        if (arr[i] < arr[end]) {
            return false;
        }
        boolean isLeftMatch = isLRD(arr, start, i - 1);
        return isLeftMatch && isLRD(arr, i, end);
    }
}
