package juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;


public class LongAdderDemo3 {

    static LongAdder counter = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            counter.reset();
            m1();
        }
    }

    public static void incr() {
        counter.increment();
    }

    private static void m1() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        int threadCount = 50;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000000; j++) {
                        incr();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("结果：%s,耗时(ms)：%s", counter.sum(), (t2 - t1)));
    }
}
