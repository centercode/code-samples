package io.github.centercode.algorithm.tree;

/**
 * 96. 不同的二叉搜索树
 * 给你一个整数n，求恰由n个节点组成且节点值从1到n互不相同的二叉搜索树有多少种？
 */
public class NumBSTrees {

    /**
     * 动态规划：
     * G(n): 长度为n的序列能构成的不同二叉搜索树的个数。
     * F(i,n): 以i为根、序列长度为n的不同二叉搜索树个数(1≤i≤n)
     *        n
     * G(n) = ∑ F(i,n), G(0)=1,G(1)=1
     *       i=1
     *
     * F(i,n) = G(i−1)⋅G(n−i)
     *
     *        n
     * G(n) = ∑ G(i−1)⋅G(n−i)
     *       i=1
     */
    public int solution1(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
