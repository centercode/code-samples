package jdk.juc.latch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class JoinDemo1 {

    private static Logger logger = LoggerFactory.getLogger(JoinDemo1.class);


    public static class T extends Thread {
        //休眠时间（秒）
        int sleepSeconds;

        public T(String name, int sleepSeconds) {
            super(name);
            this.sleepSeconds = sleepSeconds;
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
            }
            long endTime = System.currentTimeMillis();
            logger.info(",处理完毕,耗时:" + (endTime - startTime));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        T t1 = new T("解析sheet1线程", 2);
        t1.start();

        T t2 = new T("解析sheet2线程", 5);
        t2.start();

        t1.join();
        t2.join();
        long endTime = System.currentTimeMillis();
        logger.info("总耗时:" + (endTime - starTime));

    }
}