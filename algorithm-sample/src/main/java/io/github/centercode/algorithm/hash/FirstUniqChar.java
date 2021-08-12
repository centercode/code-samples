package io.github.centercode.algorithm.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 */
class FirstUniqChar {

    /**
     * 方法一：使用哈希表存储频数
     */
    public char solution1(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Boolean> isUniqueMap = new HashMap<>();
        for (char c : chars) {
            isUniqueMap.put(c, !isUniqueMap.containsKey(c));
        }
        for (char c : chars) {
            if (isUniqueMap.get(c)) {
                return c;
            }
        }

        return ' ';
    }

    /**
     * 方法二：使用哈希表存储索引
     */
    public char solution2(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            indexMap.put(c, indexMap.containsKey(c) ? -1 : i);
        }

        int first = chars.length;
        for (Map.Entry<Character, Integer> entry : indexMap.entrySet()) {
            Integer pos = entry.getValue();
            if (pos > -1 && pos < first) {
                first = pos;
            }
        }

        return first == chars.length ? ' ' : chars[first];
    }
}