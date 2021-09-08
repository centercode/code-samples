package io.github.centercode.algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 */
public class LengthOfLongestSubstring {

    /**
     * 动态规划 + 哈希表
     * dp[j] 代表以字符 s[j] 为结尾的 “最长不重复子字符串” 的长度。
     * dp[j] =
     * dp[j-1] + 1, dp[j-1] < j-i;
     * j-i, dp[j-1] >= j-i;
     */
    public int solution1(String s) {
        Map<Character, Integer> dict = new HashMap<>();
        int res = 0, tmp = 0;
        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            int i = dict.getOrDefault(ch, -1); // 获取索引 i
            dict.put(ch, j); // 更新哈希表
            tmp = tmp < j - i ? tmp + 1 : j - i; // dp[j - 1] -> dp[j]
            res = Math.max(res, tmp); // max(dp[j - 1], dp[j])
        }
        return res;
    }

    /**
     * 双指针 + 哈希表
     */
    public int solution2(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int i = -1, res = 0;
        for (int j = 0; j < s.length(); j++) {
            char ch = s.charAt(j);
            if (dic.containsKey(ch)) {
                i = Math.max(i, dic.get(ch)); // 更新左指针 i
            }
            dic.put(ch, j); // 哈希表记录
            res = Math.max(res, j - i); // 更新结果
        }
        return res;
    }
}
