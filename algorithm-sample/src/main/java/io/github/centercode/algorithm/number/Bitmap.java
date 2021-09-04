package io.github.centercode.algorithm.number;

/**
 * bitmap实现set方法，快速设置某一位为1
 * 实现test方法，快速判断某一位是否为1
 */
public class Bitmap {

    public static void set(int[] bitmap, int index) {
        int x = index / 32;
        int y = index % 32;
        bitmap[x] |= 1 << y;
    }

    public static boolean test(int[] bitmap, int index) {
        int x = index / 32;
        int y = index % 32;
        return ((bitmap[x] >>> y) & 1) == 1;
    }
}
