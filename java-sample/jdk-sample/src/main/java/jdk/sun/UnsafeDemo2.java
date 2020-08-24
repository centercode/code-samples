package jdk.sun;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * 线程调度
 */
public class UnsafeDemo2 {

    private static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        m1();
        m2();
    }

    /**
     * 调用park和unpark，模拟线程的挂起和唤醒
     */
    private static void m1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",start");
            unsafe.park(false, 0);
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",end");
        });
        thread.setName("thread1");
        thread.start();

        TimeUnit.SECONDS.sleep(5);
        unsafe.unpark(thread);
    }

    /**
     * 阻塞指定的时间
     */
    private static void m2() {
        Thread thread = new Thread(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",start");
            //线程挂起3秒
            unsafe.park(false, TimeUnit.SECONDS.toNanos(3));
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",end");
        });
        thread.setName("thread2");
        thread.start();
    }
}
