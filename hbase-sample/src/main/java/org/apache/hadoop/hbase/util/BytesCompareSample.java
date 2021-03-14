package org.apache.hadoop.hbase.util;

/**
 * compare byte array using UnsafeComparer, PureJavaComparer, JDK
 */
public class BytesCompareSample {
    public static void main(String[] args) {
        //-1, Byte.MAX_VALUE difference between hbase and jdk
        byte a = Byte.MIN_VALUE;
        byte b = (byte) (a + 1);
        byte[] left = {a};
        byte[] right = {b};
        hbaseCompareTo(Bytes.LexicographicalComparerHolder.PureJavaComparer.INSTANCE, left, right);
        hbaseCompareTo(Bytes.LexicographicalComparerHolder.UnsafeComparer.INSTANCE, left, right);
        System.out.println(JavaCompareTo(left, right));
    }

    static int hbaseCompareTo(Bytes.Comparer<byte[]> comparer, byte[] left, byte[] right) {
        int result = comparer.compareTo(left, 0, left.length, right, 0, right.length);
        System.out.println("comparer:" + comparer.getClass().getSimpleName() + ", result:" + result);
        return result;
    }

    static int JavaCompareTo(byte[] left, byte[] right) {
        for (int i = 0; i < left.length && i < right.length; i++) {
            int r = Byte.compare(left[i], right[i]);
            if (r != 0) {
                return r;
            }
        }
        return Integer.compare(left.length, right.length);
    }
}
