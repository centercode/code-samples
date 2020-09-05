package io.github.centercode.algorithm.number;

class DoublePower {

    public static void main(String[] args) {
        double v = new DoublePower().solution1(2, -6);
        System.out.println(v);
    }

    public double solution2(double x, int n) {
        if (x == 0) return 0;
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (0 < b) {
            if ((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }

    /**
     * f(x, n) = f(x, n/2)^2      (n为偶数)
     * f(x, n) = f(x, n/2)^2 * n  (n为奇数)
     */
    public double solution1(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else if (n == -1) {
            return 1 / x;
        }
        double val = solution1(x, n / 2);
        double mod = solution1(x, n % 2);
        return val * val * mod;
    }
}