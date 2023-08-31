package io.github.centercode.algorithm.number;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 */
public class SingleNumbers {

    /**
     * tc: O(n) sc:O(1)
     */
    public int[] solution1(int[] nums) {
        int x = 0;
        for (int n : nums) {
            x ^= n;
        }
        int div = 1;
        while ((x & div) == 0) {
            div <<= 1;
        }
        int a = 0;
        int b = 0;
        for (int n : nums) {
            if ((n & div) == 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }
}
