package io.github.centercode.algorithm.number;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 剑指 Offer 61. 扑克牌中的顺子
 */
public class IsStraight {
    public boolean solution1(int[] nums) {
        Arrays.sort(nums);
        // first none joker
        int joker = 0;
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) {
                joker++;
            } else if (nums[i] == nums[i + 1]) {
                return false;
            }
        }
        return nums[4] - nums[joker] < 5;
    }

    public boolean solution2(int[] nums) {
        int min = 14;
        int max = 0;
        HashSet<Integer> visited = new HashSet<>();
        for (int n : nums) {
            if (n != 0) {
                min = Math.min(min, n);
                max = Math.max(max, n);
                if (visited.contains(n)) {
                    return false;
                }
                visited.add(n);
            }
        }
        return max - min < 5;
    }
}
