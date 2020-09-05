package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 原子自增
 */
public class UnsafeDemo1 {

    private static Unsafe unsafe;

    //用来记录网站访问量，每次访问+1
    private static int count;

    //count在Demo.class对象中的地址偏移量
    private static long countOffset;

    static {
        try {
            //获取Unsafe对象
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            Field countField = UnsafeDemo1.class.getDeclaredField("count");
            //获取count字段在Demo2中的内存地址的偏移量
            countOffset = unsafe.staticFieldOffset(countField);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        unsafeRequest();
//                        synchronizedRequest();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "，" +
                " 耗时：" + (endTime - starTime) + "ms," +
                " count=" + count);
    }

    //模拟访问一次
    private static void unsafeRequest() throws InterruptedException {
        //模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        //对count原子加1
        unsafe.getAndAddInt(UnsafeDemo1.class, countOffset, 1);
    }

    private synchronized static void synchronizedRequest() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        count++;
    }
}