package io.github.centercode.algorithm.number;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 */
public class SingleNumbers {

    /**
     * tc: O(n) sc:O(1)
     */
    public int[] case1Solution1(int[] nums) {
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

    public int case2Solution1(int[] nums) {
        int[] sum = new int[32];
        for (int n : nums) {
            for (int i = 0; i < 32; i++) {
                sum[i] += n & 0x1;
                n >>>= 1;
            }
        }
        int r = 0;
        for (int i = 0; i < 32; i++) {
            r ^= (sum[i] % 3) << i;
        }
//        // 或者
//        for (int i = 0; i < 32; i++) {
//            r <<= 1;
//            r |= sum[31 - i] % 3;
//        }
        return r;
    }

    public int case2Solution2(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }
}
