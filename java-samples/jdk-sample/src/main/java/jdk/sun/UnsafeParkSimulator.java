package jdk.sun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 模拟 sun.misc.Unsafe.park(), unpark()
 */
public class UnsafeParkSimulator {

    private static Logger logger = LoggerFactory.getLogger(UnsafeParkSimulator.class);

    private static ConcurrentHashMap<Thread, Integer> permits = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            logger.info("线程Start");
            try {
                UnsafeParkSimulator.park(false, 3_000_000_000L);
            } catch (InterruptedException e) {
                logger.info("线程被唤醒");
            }
            logger.info("线程结束");
        });
        t.start();

        TimeUnit.SECONDS.sleep(1);
        UnsafeParkSimulator.unpark(t);
    }

    public static void park(boolean isAbsolute, long time) throws InterruptedException {
        Thread current = Thread.currentThread();
        //permit默认是0
        permits.computeIfAbsent(current, it -> 0);
        //如果当前线程的permit是0，那么当前线程就会阻塞
        if (permits.get(current) == 0) {
            try {
                TimeUnit.NANOSECONDS.sleep(time);
            } finally {
                //别的线程将当前线程的permit设置为1时，park方法会被唤醒，
                //然后会将permit再次设置为0，并返回。
                permits.put(current, 0);
            }
        } else {
            // 如果当前线程的permit是1，那么将permit设置为0，并立即返回。
            permits.put(current, 0);
        }
    }

    public static void unpark(Thread t) {
        permits.put(t, 1);
        t.interrupt();
    }
}
