package io.github.centercode.algorithm.string;

/**
 * LCR 097. 不同的子序列
 */
public class NumDistinct {

    /**
     * 动态规划
     */
    public int solution1(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) {
            return 0;
        }
        // dp[i][j] 表示在 s[i:] 的子序列中 t[j:] 出现的个数
        int[][] dp = new int[m + 1][n + 1];
        // 当 j=n 时，t[j:] 为空字符串，空字符串是任何字符串的子序列
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 动态规划滚动数组优化
     */
    public int solution2(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[j + 1] += dp[j];
                }
            }
        }
        return dp[n];

    }
}
