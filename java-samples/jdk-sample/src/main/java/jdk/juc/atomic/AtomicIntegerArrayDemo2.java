package jdk.juc.atomic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 统计网站页面访问量，假设网站有10个页面，
 * 现在模拟100个人并行访问每个页面10次，
 * 然后将每个页面访问量输出，应该每个页面都是1000次
 */
public class AtomicIntegerArrayDemo2 {

    private static Logger logger = LoggerFactory.getLogger(AtomicIntegerArrayDemo2.class);

    static int pageSize = 10;

    static int threadSize = 100;

    static AtomicIntegerArray pageCounter = new AtomicIntegerArray(new int[pageSize]);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            new Thread(() -> {
                try {
                    for (int p = 0; p < pageSize; p++) {
                        request(p, 10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        printResult();
    }

    private static void printResult() {
        for (int i = 0; i < pageSize; i++) {
            logger.info("第" + (i + 1) + "个页面访问次数为" + pageCounter.get(i));
        }
    }

    /**
     * 模拟访问times次
     */
    public static void request(int page, int times) throws InterruptedException {
        for (int j = 0; j < times; j++) {
            //模拟耗时5毫秒
            TimeUnit.MILLISECONDS.sleep(5);
            pageCounter.incrementAndGet(page);
        }
    }
}
