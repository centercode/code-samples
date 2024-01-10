package io.github.centercode.starrocks.udf;

/**
 * UDTF sample: split(STRING).
 */
public class SplitUDTF {

    public String[] process(String str) {
        if (str == null) return null;
        return str.split(" ");
    }

}
