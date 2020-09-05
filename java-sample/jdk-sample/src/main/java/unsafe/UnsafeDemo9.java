package unsafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 绕过构造方法创建对象
 */
public class UnsafeDemo9 {

    private static Logger logger = LoggerFactory.getLogger(UnsafeDemo9.class);

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
        private String name;

        private C1() {
            logger.info("C1 default constructor!");
        }

        private C1(String name) {
            this.name = name;
            logger.info("C1 有参 constructor!");
        }
    }

    public static void main(String[] args) throws InstantiationException {
        logger.info(String.valueOf(unsafe.allocateInstance(C1.class)));
    }
}
