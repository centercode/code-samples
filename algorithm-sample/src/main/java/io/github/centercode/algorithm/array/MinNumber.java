package io.github.centercode.algorithm.array;

import java.util.ArrayList;

public class MinNumber {
    public String solution1(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        list.sort((a, b) -> {
            String s1 = String.valueOf(a) + b;
            String s2 = String.valueOf(b) + a;
            return s1.compareTo(s2);
        });

        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i);
        }
        return sb.toString();
    }
}
