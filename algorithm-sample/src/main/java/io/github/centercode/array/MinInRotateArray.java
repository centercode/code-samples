package io.github.centercode.array;

class MinInRotateArray {

    public int solution1(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int m = low + (high - low) / 2;
            if (numbers[m] < numbers[high]) {
                high = m;
            } else if (numbers[high] < numbers[m]) {
                low = m + 1;
            } else {
                high--;
            }
        }
        return numbers[low];
    }
}