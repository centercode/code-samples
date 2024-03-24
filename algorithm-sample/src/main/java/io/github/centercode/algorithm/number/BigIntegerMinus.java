package io.github.centercode.algorithm.number;

import java.util.Arrays;

public class BigIntegerMinus {

    public static void main(String[] args) {
        BigIntegerMinus sub = new BigIntegerMinus();
        String r = sub.sub("54321", "12345");
        System.out.println(r);
    }

    public String sub(String a, String b) {
        char[] arr1 = a.toCharArray();
        char[] arr2 = b.toCharArray();
        int c = compare(arr1, arr2);
        if (c == 0) {
            return "0";
        } else if (c < 0) {
            return "-" + sub0(arr2, arr1);
        } else {
            return sub0(arr1, arr2);
        }
    }


    private int compare(char[] a, char[] b) {
        if (a.length < b.length) {
            return -1;
        } else if (a.length > b.length) {
            return 1;
        } else {
            for (int i = 0; i < a.length; i++) {
                if (a[i] > b[i]) {
                    return 1;
                } else if (a[i] < b[i]) {
                    return -1;
                }
            }
            return 0;
        }
    }

    //a >= b
    private String sub0(char[] a, char[] b) {
        char[] result = new char[a.length + b.length];
        Arrays.fill(result, '0');
        int i = a.length - 1, j = b.length - 1, k = 0;
        while (0 <= i && 0 <= j) {
            int m = a[i] - '0';
            int n = b[j] - '0';
            if (m >= n) {
                result[k] += m - n;
            } else {
                a[i - 1]--;
                result[k] += m + 10 - n;
            }
            i--;
            j--;
            k++;
        }
        i = 0;
        j = k - 1;
        while (i < j) {
            swap(result, i, j);
            i++;
            j--;
        }
        return new String(result, 0, k);
    }

    private void swap(char[] result, int i, int k) {
        char tmp = result[i];
        result[i] = result[k];
        result[k] = tmp;
    }
}
