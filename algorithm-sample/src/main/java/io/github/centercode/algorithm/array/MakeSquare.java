package io.github.centercode.algorithm.array;

import java.util.Arrays;

/**
 * 473. 火柴拼正方形
 */
public class MakeSquare {

    /**
     * 方法一：回溯
     * tc: O(4^n), sc: O(n)
     */
    public boolean solution1(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        int[] buckets = new int[4];
        return dfs(matchsticks, buckets, sum / 4, matchsticks.length - 1);
    }

    /**
     * @param matchsticks 所有火柴长度数组
     * @param buckets 正方形四条边已经放入的火柴累积长度
     * @param target 目标正方形的边长
     * @param index 火柴序号
     */
    private boolean dfs(int[] matchsticks, int[] buckets, int target, int index) {
        // 所有火柴枚举完毕
        if (index == -1) {
            return true;
        }
        for (int i = 0; i < buckets.length; i++) {
            if (i > 0 && buckets[i] == buckets[i - 1]) {
                continue;
            }
            // 减枝：如果将第index个火柴放入第i条边超过了目标正方形的边长
            // 当所有火柴枚举完毕并且都满足此条件时，buckets的每个累积长度都正好等于target，既证明有解
            if (buckets[i] + matchsticks[index] > target) {
                continue;
            }
            // 尝试将第index个火柴放入第i条边
            buckets[i] += matchsticks[index];
            // 继续枚举第index+1根火柴的放置情况
            if (dfs(matchsticks, buckets, target, index - 1)) {
                return true;
            }
            // 撤回将第index个火柴放入第i条边，继续尝试第i+1条边
            buckets[i] -= matchsticks[index];
        }
        return false;
    }

    /**
     * 方法二：状态压缩 + 动态规划
     * tc: O(n 2^n), sc: O(2^n)
     */
    public boolean solution2(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        int n = matchsticks.length, targetLen = sum / 4;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int s = 0; s < (1 << n); s++) {
            for (int i = 0; i < n; i++) {
                // 状态s的第i位为0
                if ((s & (1 << i)) == 0) {
                    continue;
                }
                // 状态r为状态s的第i位置为0得到
                int r = s & ~(1 << i);
                if (dp[r] >= 0 && dp[r] + matchsticks[i] <= targetLen) {
                    dp[s] = (dp[r] + matchsticks[i]) % targetLen;
                    break;
                }
            }
        }
        return dp[(1 << n) - 1] == 0;
    }

}
