package io.github.centercode.algorithm.string;

/**
 * 字符串转换为整数
 */
public class SimpleStringToInt {

    int solution1(String s) {
        return Integer.parseInt(s);
    }

    int solution2(String s) {
        if (null == s || s.length() == 0) {
            throw new IllegalArgumentException();
        }
        boolean positive = true;
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i == 0 && c == '-') {
                positive = false;
                continue;
            } else if (i == 0 && c == '+') {
                continue;
            }
            int n = c - '0';
            checkRange(n);
            num = num * 10 + n;
        }
        if (!positive) {
            num = ~num + 1;
        }
        return num;
    }

    private void checkRange(int n) {
        if (n < 0 || 9 < n) {
            throw new IllegalStateException();
        }
    }
}
