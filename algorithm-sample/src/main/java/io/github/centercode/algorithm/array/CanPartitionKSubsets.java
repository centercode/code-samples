package io.github.centercode.algorithm.array;

import java.util.Arrays;

/**
 * 698. 划分为k个相等的子集：
 * 给定一个整数数组，找出是否有可能把这个数组分成k个非空子集，其总和都相等。
 */
public class CanPartitionKSubsets {

    /**
     * 方法一：状态压缩 + 记忆化搜索
     */
    public boolean solution1(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        int avg = sum / k;
        int n = nums.length;
        // 不能有超过avg的数字
        if (nums[n - 1] > avg) {
            return false;
        }
        boolean[] dp = new boolean[1 << n];
        Arrays.fill(dp, true);
        return dfs(nums, avg, dp, (1 << n) - 1, 0);
    }

    /**
     * @param nums 所有数字
     * @param avg 每个子集加和的目标值
     * @param dp dp[s]代表状态s是否可行
     * @param s 长度为n的状态：二进制位为1代表该数字可用，否则表示已经被使用
     * @param accumulation 所有已经选择过的可行数字的累积和对avg求余数
     */
    boolean dfs(int[] nums, int avg, boolean[] dp, int s, int accumulation) {
        // 枚举完所有情况
        if (s == 0) {
            return true;
        }
        if (!dp[s]) {
            return false;
        }
        dp[s] = false;
        for (int i = 0; i < nums.length; i++) {
            // 减枝
            if (accumulation + nums[i] > avg) {
                break;
            }
            // 如果状态s的第i位为1
            if (((s >>> i) & 1) != 0) {
                // 将状态s的第i位置为0，累加num[i]并递归
                if (dfs(nums, avg, dp, s ^ (1 << i), (accumulation + nums[i]) % avg)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 方法二：状态压缩 + 动态规划
     */
    public boolean solution2(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        int avg = sum / k;
        int n = nums.length;
        if (nums[n - 1] > avg) {
            return false;
        }
        // accumulation[s]代表状态s对应所有数字的累加
        int[] accumulation = new int[1 << n];
        // dp[s]代表状态s是否可行
        boolean[] dp = new boolean[1 << n];
        dp[0] = true;
        // s: 长度为n的状态：二进制位为1代表该数字可用，否则表示已经被使用
        for (int s = 0; s < (1 << n); s++) {
            if (!dp[s]) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                if (accumulation[s] + nums[i] > avg) {
                    break;
                }
                // 如果状态s的第i位为0
                if (((s >>> i) & 1) == 0) {
                    // 将状态s的第i位置为1得到状态t
                    int t = s | (1 << i);
                    if (!dp[t]) {
                        accumulation[t] = (accumulation[s] + nums[i]) % avg;
                        dp[t] = true;
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}
