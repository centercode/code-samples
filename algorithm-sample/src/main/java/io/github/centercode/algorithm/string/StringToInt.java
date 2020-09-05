package io.github.centercode.algorithm.string;

/**
 * https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 */
public class StringToInt {

    int solution2(String str) {
        if (null == str || str.length() == 0) {
            return 0;
        }
        int sign = 1;
        int result = 0;
        int index = 0;
        int border = Integer.MAX_VALUE / 10;
        while (index < str.length() && str.charAt(index) == ' ') {
            index++;
        }
        if (str.length() <= index) {
            return 0;
        } else if (str.charAt(index) == '-') {
            sign = -1;
            index++;
        } else if (str.charAt(index) == '+') {
            index++;
        } else if (notValid(str.charAt(index))) {
            return 0;
        }
        for (; index < str.length(); index++) {
            char c = str.charAt(index);
            if (notValid(c)) {
                break;
            }
            if (border < result || (border == result && '7' < c)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + c - '0';
        }
        return result * sign;
    }

    private boolean notValid(char n) {
        return n < '0' || '9' < n;
    }
}
