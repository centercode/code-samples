package io.github.centercode.algorithm.number;

/**
 * No.17 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
 */
class PrintNumbers {

    public int[] solution1(int n) {
        int end = (int) Math.pow(10, n) - 1;
        int[] res = new int[end];
        for (int i = 0; i < end; i++)
            res[i] = i + 1;
        return res;
    }
}