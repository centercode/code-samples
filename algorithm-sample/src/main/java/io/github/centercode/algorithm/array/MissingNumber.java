package io.github.centercode.algorithm.array;

class MissingNumber {

    public int solution1(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (m < nums[m]) {
                j = m - 1;
            } else if (m == nums[m]) {
                i = m + 1;
            }
        }
        return i;
    }
}