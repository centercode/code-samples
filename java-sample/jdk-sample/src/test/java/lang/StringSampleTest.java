package lang;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class StringSampleTest {

    @Test
    public void intern1() {
        // JDK1.7：在堆中创建字符串对象，将字符串对象的引用保存在字符串常量池中
        String s1 = "Java";
        // 直接返回字符串常量池中字符串对象的引用
        String s2 = s1.intern();
        // 在堆中创建一个字符串对象
        String s3 = new String("Java");
        String s4 = s3.intern();
        assertSame(s1, s2);
        assertNotSame(s3, s4);
        assertSame(s1, s4);
    }


    @Test
    public void intern2() {
        Random random = new Random();
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < 12800000; i++) {
            String s = String.valueOf(random.nextInt(1000)).intern();
            res.add(s);
        }
    }
}