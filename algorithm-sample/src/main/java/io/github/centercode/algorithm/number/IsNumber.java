package io.github.centercode.algorithm.number;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 20. 表示数值的字符串
 */
public class IsNumber {

    /**
     * 有限状态自动机
     */
    public boolean solution1(String s) {
        Map<Character, Integer>[] states = new Map[] {
            new HashMap<Character, Integer>() {{
                put(' ', 0);
                put('s', 1);
                put('d', 2);
                put('.', 4);
            }}, // 0.
            new HashMap<Character, Integer>() {{
                put('d', 2);
                put('.', 4);
            }},                           // 1.
            new HashMap<Character, Integer>() {{
                put('d', 2);
                put('.', 3);
                put('e', 5);
                put(' ', 8);
            }}, // 2.
            new HashMap<Character, Integer>() {{
                put('d', 3);
                put('e', 5);
                put(' ', 8);
            }},              // 3.
            new HashMap<Character, Integer>() {{
                put('d', 3);
            }},                                        // 4.
            new HashMap<Character, Integer>() {{
                put('s', 6);
                put('d', 7);
            }},                           // 5.
            new HashMap<Character, Integer>() {{
                put('d', 7);
            }},                                        // 6.
            new HashMap<Character, Integer>() {{
                put('d', 7);
                put(' ', 8);
            }},                           // 7.
            new HashMap<Character, Integer>() {{
                put(' ', 8);
            }}                                         // 8.
        };
        int p = 0;
        char t;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                t = 'd';
            } else if (c == '+' || c == '-') {
                t = 's';
            } else if (c == 'e' || c == 'E') {
                t = 'e';
            } else if (c == '.' || c == ' ') {
                t = c;
            } else {
                t = '?';
            }
            if (!states[p].containsKey(t)) {
                return false;
            }
            p = (int) states[p].get(t);
        }
        return p == 2 || p == 3 || p == 7 || p == 8;
    }
}
