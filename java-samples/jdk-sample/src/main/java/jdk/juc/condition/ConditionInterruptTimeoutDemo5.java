package jdk.juc.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionInterruptTimeoutDemo5 {

    private static final Logger logger = LoggerFactory.getLogger(ConditionInterruptTimeoutDemo5.class);

    static ReentrantLock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    public static class T1 extends Thread {

        @Override
        public void run() {
            lock.lock();
            try {
                logger.info(getName() + ",start");
//                logger.info(getName() + "," + condition.await(5, TimeUnit.SECONDS));
                logger.info(getName() + "," + condition.awaitNanos(TimeUnit.SECONDS.toNanos(5)));

                logger.info(getName() + ",end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.start();
//        interrupt();
    }

    private static void interrupt() throws InterruptedException {
        //休眠1秒之后，唤醒t1线程
        TimeUnit.SECONDS.sleep(1);
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
