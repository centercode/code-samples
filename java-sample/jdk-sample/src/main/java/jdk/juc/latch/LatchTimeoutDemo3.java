package jdk.juc.latch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 等待指定的时间
 */
public class LatchTimeoutDemo3 {

    private static Logger logger = LoggerFactory.getLogger(LatchTimeoutDemo3.class);

    public static class T extends Thread {
        //休眠时间（秒）
        int sleepSeconds;
        CountDownLatch countDownLatch;

        public T(String name, int sleepSeconds, CountDownLatch countDownLatch) {
            super(name);
            this.sleepSeconds = sleepSeconds;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            logger.info(",开始处理!");
            try {
                //模拟耗时操作，休眠sleepSeconds秒
                TimeUnit.SECONDS.sleep(this.sleepSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
            long endTime = System.currentTimeMillis();
            logger.info(",处理完毕,耗时:" + (endTime - startTime));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        logger.info("线程 start!");
        CountDownLatch countDownLatch = new CountDownLatch(2);
        long starTime = System.currentTimeMillis();
        T t1 = new T("解析sheet1线程", 2, countDownLatch);
        t1.start();

        T t2 = new T("解析sheet2线程", 5, countDownLatch);
        t2.start();
        boolean result = countDownLatch.await(2, TimeUnit.SECONDS);
        logger.info("线程 end!");
        long endTime = System.currentTimeMillis();
        logger.info("主线程耗时:" + (endTime - starTime) + ",result:" + result);

    }
}
