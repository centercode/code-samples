package io.github.centercode.algorithm.dp;

/**
 * 剑指 Offer 14- 剪绳子
 */
public class CuttingRope {

    /**
     * 动态规划法
     * tc=O(n^2), sc=O(n)
     *
     * @param n 2 <= n <= 58
     */
    public int case1Solution1(int n) {
        int[] dp = new int[n + 1];
//        dp[0] = 0;
//        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
//            dp[i] = 0;
            for (int j = 1; j < i; j++) {
                // 当第一段长度为j时，dp[i]的最大值
                int jMax = Math.max(j * (i - j), j * dp[i - j]);
                // 所有情况下(j长度属于[1,i)时)，dp[i]的最大值
                dp[i] = Math.max(dp[i], jMax);
            }
        }
        return dp[n];
    }

    /**
     * 公式推导
     * tc=O(1), sc=O(1)
     */
    public int case1Solution2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3, b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        }
        return (int) Math.pow(3, a) * 2;
    }

    /**
     * @param n 2 <= n <= 1000
     */
    public int case2Solution1(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3, b = n % 3, p = 1000000007;
        long rem = 1, x = 3;
        for (a = a - 1; a > 0; a /= 2) {
            if (a % 2 == 1) {
                rem = (rem * x) % p;
            }
            x = (x * x) % p;
        }
        if (b == 0) {
            return (int) (rem * 3 % p);
        }
        if (b == 1) {
            return (int) (rem * 4 % p);
        }
        return (int) (rem * 6 % p);
    }
}
