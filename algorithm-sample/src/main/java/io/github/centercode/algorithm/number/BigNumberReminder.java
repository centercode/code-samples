package io.github.centercode.algorithm.number;

/**
 * (x * y) % p = ((x%p) * (y%p)) % p
 */
public class BigNumberReminder {
    /**
     * 循环求余: (x^a) % p = ((x^(a-1) % p ) * (x % p)) % p = (x^(a-1) %p * x) % p
     *
     * @param x 基数
     * @param a 幂次方
     * @param p 被除数
     * @return (x ^ a) % p
     */
    long solution1(int x, int a, int p) {
        long rem = 1;
        for (int i = 0; i < a; i++) {
            rem = (rem * x) % p;
        }
        return rem;
    }

    /**
     * 快速幂求余: (x^a) % p = (x^2)^(a/2) % p
     * (x^a) % p = ((x^2 %p))^(a/2) % p, a为偶数
     * (x^a) % p = (x*((x^2 %p))^(a/2)) % p, a为奇数
     *
     * @param x 基数
     * @param a 幂次方
     * @param p 被除数
     * @return (x ^ a) % p
     */
    long solution2(int x, int a, int p) {
        long rem = 1;
        for (; a > 0; a /= 2) {
            if ((a & 0x1) == 1) {
                rem = (rem * x) % p;
            }
            x = (x ^ 2) % p;
        }
        return rem;
    }

}
