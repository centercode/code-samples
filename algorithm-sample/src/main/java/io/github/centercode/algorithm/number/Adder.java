package io.github.centercode.algorithm.number;

/**
 * 剑指 Offer 65. 不用加减乘除做加法
 */
public class Adder {
    public int solution1(int a, int b) {
        while (b != 0) {
            int sum = (a & b) << 1;
            a = a ^ b;
            b = sum;
        }
        return a;
    }
}
