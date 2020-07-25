package jdk.juc.atomic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo1 {

    private static Logger logger = LoggerFactory.getLogger(CASDemo1.class);

    private interface Counter {

        void increase() throws InterruptedException;

        int getCount();
    }

    private static Counter lockCounter = new Counter() {

        private int count = 0;

        /**
         * synchronized
         */
        @Override
        public void increase() throws InterruptedException {
            //模拟耗时5毫秒
            TimeUnit.MILLISECONDS.sleep(5);

            synchronized (this.getClass()) {
                count = count + 1;
            }
        }

        @Override
        public int getCount() {
            return count;
        }
    };

    private static Counter mockCASCounter = new Counter() {

        private int count = 0;

        @Override
        public void increase() throws InterruptedException {
            //模拟耗时5毫秒
            TimeUnit.MILLISECONDS.sleep(5);
            int expect;
            do {
                expect = count;
            } while (!compareAndSwap(expect, expect + 1));
        }

        /**
         * 判断count当前值是否和expect一样，如果一样将update赋值给count
         */
        public synchronized boolean compareAndSwap(int expect, int update) {
            if (count == expect) {
                count = update;
                return true;
            }
            return false;
        }

        @Override
        public int getCount() {
            return count;
        }
    };

    private static Counter atomicCounter = new Counter() {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public void increase() throws InterruptedException {
            //模拟耗时5毫秒
            TimeUnit.MILLISECONDS.sleep(5);
            count.incrementAndGet();
        }

        @Override
        public int getCount() {
            return count.get();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        final Counter counter = mockCASCounter;
        int threadSize = 100;
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        counter.increase();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
            thread.start();
        }
        latch.await();
        logger.info("，耗时：" + (System.currentTimeMillis() - start) + "ms, count=" + counter.getCount());
    }
}
