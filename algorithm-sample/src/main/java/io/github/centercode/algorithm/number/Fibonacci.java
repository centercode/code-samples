package io.github.centercode.algorithm.number;

public class Fibonacci {

    public int solution1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int v1 = 0, v2 = 1;

        for (int i = 2; i <= n; i++) {
            int v3 = (v1 + v2) % 1000000007;
            v1 = v2;
            v2 = v3;
        }
        return v2;
    }
}
