package lang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 泛型使用相关示例
 */
public class GenericSample<T> {

    /**
     * error: method set in interface List<E> cannot be applied to given types;
     * required: int,CAP#1
     * found: int,CAP#2
     * reason: argument mismatch; Object cannot be converted to CAP#1
     */
    void wildcardError(List<?> list) {
        // list.set(0, list.get(0));
    }

    /**
     * 添加编译参数-Xlint:rawtypes后的警告信息：
     * warning: [rawtypes] found raw type: List, missing type arguments for generic class List<E>
     */
    private void rawTypes() {
        List list;
    }

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

    /**
     * warning: [unchecked] Possible heap pollution from parameterized vararg type List<String>
     */
    private void heapPollution(List<String>... strList) {
        Object[] objArray = strList;
        objArray[0] = Collections.singletonList(123);
        System.out.println(strList[0].get(0));
    }
}
