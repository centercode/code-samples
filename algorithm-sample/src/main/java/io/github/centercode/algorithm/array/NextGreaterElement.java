package io.github.centercode.algorithm.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * 503. 下一个更大元素 II
 * 给定一个循环数组，输出每个元素的下一个更大元素
 */
public class NextGreaterElement {

    /**
     * 单调栈
     */
    int[] solution(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * nums.length - 1; i++) {
            int idx = i % nums.length;
            while (!stack.empty() && nums[idx] > nums[stack.peek()]) {
                res[stack.pop()] = nums[idx];
            }
            stack.push(idx);
        }

        return res;
    }
}
