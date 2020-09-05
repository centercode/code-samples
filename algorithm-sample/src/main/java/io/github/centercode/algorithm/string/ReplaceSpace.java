package io.github.centercode.algorithm.string;

public class ReplaceSpace {

    public String solution1(String s) {
        return s.replace(" ", "%20");
    }

    public String solution2(String s) {
        if (null == s || "".equals(s)) {
            return s;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }
        }
        if (count == 0) {
            return s;
        }
        char[] chars = new char[s.length() + 2 * count];
        int j = 0;
        for (int i = 0; i < s.length(); i++, j++) {
            if (s.charAt(i) == ' ') {
                chars[j++] = '%';
                chars[j++] = '2';
                chars[j] = '0';
            } else {
                chars[j] = s.charAt(i);
            }
        }
        return new String(chars);
    }
}
