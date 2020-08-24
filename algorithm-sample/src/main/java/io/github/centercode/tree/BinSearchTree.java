package io.github.centercode.tree;

/**
 * 判断数组是不是二叉搜索树的后序遍历
 * 数组的任意两个数字都互不相同
 * 例如：
 * {5,7,6,9,11,10,8} => true
 * {7,4,6,5}         => false
 */
public class BinSearchTree {

    public static void main(String[] args) {
        int[] arr = {5, 7, 6, 9, 11, 10, 8};
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

    /**
     * 判断从start 到 end 的一个子树是否是后序遍历
     * 其中最后一个结点是子树的根节点
     *
     * @param start 子树在数组中的第一个结点，include
     * @param end   子树在数组中最后一个结点，include
     */
    boolean isLRD(int[] arr, int start, int end) {
        if (end <= start) {
            return true;
        }
        int i = start;
        // 如果存在左子树，则左子树都比根节点的值小
        while (i < end && arr[i] < arr[end]) {
            i++;
        }// i 越过左子树, start ≦ i ≦ (end-1)
        // 如果存在右子树，则右子树都比根节点的值大
        for (int j = i; j < end; j++) {
            if (arr[j] < arr[end]) {
                return false;
            }
        }
        // i == start 无左子树
        boolean isLeftMatch = (i == start) || isLRD(arr, start, i - 1);
        // i == end-1 无右子树
        return isLeftMatch && (i == end - 1) || isLRD(arr, i, end - 1);
    }
}
