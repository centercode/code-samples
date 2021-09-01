package io.github.centercode.algorithm.number;

/**
 * 面试题 05.03. 翻转数位
 * 给定一个32位整数 num，你可以将一个数位从0变为1。
 * 请编写一个程序，找出你能够获得的最长的一串1的长度。
 */
public class MaxContinuousBits {

    public int solution1(int num) {
        int cur = 0;
        int insert = 0;
        int res = 1;

        for (int i = 0; i < 32; i++) {
            if ((num & (0x1 << i)) != 0) {
                cur += 1;
                insert += 1;
            } else {
                insert = cur + 1;
                cur = 0;
            }
            res = Math.max(res, insert);
        }
        return res;
    }
}
