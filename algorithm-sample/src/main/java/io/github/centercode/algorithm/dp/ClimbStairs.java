package io.github.centercode.algorithm.dp;

class ClimbStairs {

    /**
     * 70. 爬楼梯
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 动态规划
     */
    public int case1Solution1(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 70. 爬楼梯
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 矩阵快速幂
     */
    public int case1Solution2(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }

    /**
     * 746. 使用最小花费爬楼梯
     * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
     * 请你找出达到楼层顶部的最低花费。
     * 输入：cost = [10, 15, 20]
     * 输出：15
     */
    public int case2Solution1(int[] cost) {
        // 开始从平地跳到第0阶和第1阶的花费都是0
        int p = 0, q = 0;
        for (int i = 2; i <= cost.length; i++) {
            int r = Math.min(p + cost[i - 2], q + cost[i - 1]);
            p = q;
            q = r;
        }
        return q;
    }

}