package juc.atomic;

import util.Threads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 多线程并发调用一个类的初始化方法，如果未被初始化过，将执行初始化工作，要求只能初始化一次
 */
public class AtomicReferenceFieldUpdaterDemo5 {

    private static Logger logger = LoggerFactory.getLogger(AtomicReferenceFieldUpdaterDemo5.class);

    static class T {

        //init用来标注是否被初始化过
        volatile Boolean init = Boolean.FALSE;

        AtomicReferenceFieldUpdater<T, Boolean> initUpdater = AtomicReferenceFieldUpdater.newUpdater(
                T.class,
                Boolean.class,
                "init"
        );

        public void init() {
            //isInit为false的时候，才进行初始化，并将isInit采用原子操作置为true
            if (initUpdater.compareAndSet(this, Boolean.FALSE, Boolean.TRUE)) {
                logger.info("开始初始化!");
                //模拟休眠3秒
                Threads.silentSleep(3, TimeUnit.SECONDS);
                logger.info("初始化完毕!");
            } else {
                logger.info("有其他线程已经执行了初始化!");
            }
        }
    }

    public static void main(String[] args) {
        T obj = new T();
        for (int i = 0; i < 5; i++) {
            new Thread(obj::init).start();
        }
    }
}
