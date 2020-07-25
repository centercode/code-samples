package jdk.sun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 示例：shouldBeInitialized、ensureClassInitialized
 */
public class UnsafeDemo8 {

    private static Logger logger = LoggerFactory.getLogger(UnsafeDemo8.class);

    static Unsafe unsafe;

    static {
        //获取Unsafe对象
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class C1 {

        private static int count;

        static {
            count = 10;
            logger.info("C1 static init.");
        }
    }

    static class C2 {

        private static int count;

        static {
            count = 11;
            logger.info("C2 static init.");
        }
    }

    public static void main(String[] args) {
        // 判断C1类是需要需要初始化，
        // 如果已经初始化了，会返回false，
        // 如果此类没有被初始化过，返回true
        if (unsafe.shouldBeInitialized(C1.class)) {
            logger.info("C1需要进行初始化");
            //对C1进行初始化
            unsafe.ensureClassInitialized(C1.class);
        }
        logger.info(String.valueOf(C2.count));
        logger.info(String.valueOf(unsafe.shouldBeInitialized(C1.class)));
    }
}
