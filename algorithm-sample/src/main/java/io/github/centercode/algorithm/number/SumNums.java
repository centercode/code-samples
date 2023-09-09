package io.github.centercode.algorithm.number;

/**
 * 剑指 Offer 64. 求1+2+…+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class SumNums {

    /**
     * 递归
     * tc: O(n), sc: O(n)
     */
    public int solution1(int n) {
        boolean flag = (n != 0) && (n += solution1(n - 1)) > 0;
        return n;
    }

    /**
     * 快速乘: 等差数列求和公式 + 俄罗斯农民乘法 + 二进制展开(题目数据范围n为[1,10000], 最多不会超过14位)
     */
    public int solution2(int n) {
        int ans = 0, a = n, b = n + 1;
        boolean flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        flag = ((b & 1) == 1) && (ans += a) > 0;
        a <<= 1;
        b >>= 1;
        return ans >> 1;
    }
}
