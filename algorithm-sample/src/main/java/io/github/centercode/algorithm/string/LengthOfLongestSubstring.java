package io.github.centercode.algorithm.string;

import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串
 */
public class LengthOfLongestSubstring {

    /**
     * 滑动窗口
     * tc:O(n), sc:O(1)
     */
    public int solution1(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        // l: left bound, included
        int l = 0;
        // r: right bound, included
        for (int r = 0; r < s.length(); r++) {
            if (map.containsKey(s.charAt(r))) {
                // 距离当前位置最近的不重复字符作为下一次的左边界
                l = Math.max(l, map.get(s.charAt(r)) + 1);
            }
            map.put(s.charAt(r), r);
            max = Math.max(max, r - l + 1);
        }
        return max;
    }

    /**
     * 动态规划：
     * 设dp[j]代表以字符s[j]为结尾的 “最长不重复子字符串” 的长度
     * dp[j]=
     * dp[j-1]+1, dp[j-1]  < j-i
     * j-i      , dp[j-1] >= j-i
     */
    public int solution2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int dp = 0;
        int res = 0;
        for (int j = 0; j < s.length(); j++) {
            // map[i]为最近重复字符的下标
            int i = map.getOrDefault(s.charAt(j), -1);
            if (dp < j - i) {
                dp = dp + 1;
            } else {
                dp = j - i;
            }
            res = Math.max(res, dp);
            // 更新最靠后的该字符的下标
            map.put(s.charAt(j), j);
        }
        return res;
    }

}
