package lang;

import java.util.ArrayList;

/**
 * 泛型使用相关示例
 */
public class GenericSample<T> {

    /**
     * 警告信息：
     * Note: GenericSample.java uses unchecked or unsafe operations.
     * <p>
     * 添加编译参数-Xlint:unchecked后的警告信息：
     * warning: [unchecked] unchecked conversion
     */
    private void uncheckedConversion() {
        ArrayList<Integer> list = new ArrayList();
    }

    /**
     * 代码注释打开后的警告信息：
     * error: non-static type variable T cannot be referenced from a static context
     */
    private static void staticContextReferenceNonStaticTypeVariable() {
        // T t;
    }
}
