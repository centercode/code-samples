package juc.condition;

import utils.Threads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition必须加锁使用
 */
public class ConditionBasicDemo2 {

    private static final Logger logger = LoggerFactory.getLogger(ConditionBasicDemo2.class);

    static ReentrantLock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    public static class T1 extends Thread {

        public T1() {
            super(T1.class.getSimpleName());
        }

        @Override
        public void run() {
            logger.info(getName() + "准备获取锁!");
            lock.lock();
            try {
                logger.info(getName() + "获取锁成功!");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            logger.info(getName() + "释放锁成功!");
        }
    }

    public static class T2 extends Thread {

        public T2() {
            super(T2.class.getSimpleName());
        }

        @Override
        public void run() {
            logger.info(getName() + "准备获取锁!");
            lock.lock();
            try {
                logger.info(getName() + "获取锁成功!");
                condition.signal();
                logger.info(getName() + " signal!, Sleeping...");
                Threads.silentSleep(5, TimeUnit.SECONDS);
                logger.info(getName() + "准备释放锁!");
            } finally {
                lock.unlock();
                logger.info(getName() + "释放锁成功!");
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        t1.start();
        Threads.silentSleep(5, TimeUnit.SECONDS);
        T2 t2 = new T2();
        t2.start();
    }
}
