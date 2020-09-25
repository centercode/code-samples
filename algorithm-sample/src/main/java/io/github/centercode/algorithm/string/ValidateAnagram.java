package io.github.centercode.algorithm.string;

/**
 * 给定两个字符串 s 和 t
 * 编写一个函数来判断 t 是否是 s 的一个字母异位词。
 */
public class ValidateAnagram {

    public boolean solution1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] tab = new int[26];
        for (int i = 0; i < s.length(); i++) {
            tab[s.charAt(i) - 'a']++;
            tab[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (tab[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
