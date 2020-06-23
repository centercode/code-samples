package jdk.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程t1中断标志的变换过程：false->true->false
 */
public class ConditionInterruptDemo4 {

    private static final Logger logger = LoggerFactory.getLogger(ConditionInterruptDemo4.class);

    static ReentrantLock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    public static class T1 extends Thread {

        public T1() {
            super(T1.class.getSimpleName());
        }

        @Override
        public void run() {
            lock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                logger.info("catch InterruptedException后中断标志：" + this.isInterrupted());
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //给t1线程发送中断信号
        logger.info("中断前t1中断标志：" + t1.isInterrupted());
        t1.interrupt();
        logger.info("中断后t1中断标志：" + t1.isInterrupted());
    }
}
