package io.github.centercode.starrocks.udf;

/**
 * Scalar UDF sample: toUpperCase(STRING).
 */
public class ToUpperCaseUDF {

    /**
     * evaluate 方法为 UDF 调用入口，必须是 public 成员方法。
     * @return 返回类型必须为包装类型
     */
    public String evaluate(String s) {
        if (s == null) {
            return null;
        }
        return s.toUpperCase();
    }
}
