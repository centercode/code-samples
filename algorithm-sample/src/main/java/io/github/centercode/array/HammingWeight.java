package io.github.centercode.array;

public class HammingWeight {

    public static void main(String[] args) {
        int i = new HammingWeight().solution1(9);
        System.out.println(i);
    }

    // you need to treat n as an unsigned value
    public int solution1(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    // you need to treat n as an unsigned value
    public int solution2(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }
}