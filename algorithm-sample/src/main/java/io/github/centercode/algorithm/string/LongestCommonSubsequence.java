package io.github.centercode.algorithm.string;

/**
 * 1143. 最长公共子序列长度
 */
public class LongestCommonSubsequence {

    /**
     * 动态规划
     * dp[i][j] 表示text1[0:i]和text2[0:j]的最长公共自子序列长度
     * dp[i][j] = dp[i-1][j-1] + 1,             当 text[i-1]==text[j-1]
     * dp[i][j] = max(dp[i-1][j], dp[i][j-1]),  当 text[i-1]!=text[j-1]
     */
    public int solution1(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // dp[0][j], dp[i][0]全为0
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 优化为一维数组
     */
    public int solution2(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1, pre = 0; j <= n; j++) {
                int tmp = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = pre + 1;
                } else {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                pre = tmp;
            }

        }

        return dp[n];
    }

}
