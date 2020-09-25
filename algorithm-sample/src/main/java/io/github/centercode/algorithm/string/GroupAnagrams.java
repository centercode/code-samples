package io.github.centercode.algorithm.string;

import java.util.*;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。
 * 字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 所有输入均为小写字母
 * 不考虑答案输出的顺序
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        GroupAnagrams anagrams = new GroupAnagrams();
        List<List<String>> transform = anagrams.solution1(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(transform);
    }

    List<List<String>> solution1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return Collections.emptyList();
        }
        int[] flags = new int[26];
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            Arrays.fill(flags, 0);
            for (char c : str.toCharArray()) {
                flags[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(flags[i]);
            }
            List<String> bucket = map.computeIfAbsent(sb.toString(), m -> new ArrayList<>());
            bucket.add(str);
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}
