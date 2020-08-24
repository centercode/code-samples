package jdk.juc.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 扩展线程池
 */
public class ThreadPoolDemo6 {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolDemo6.class);

    static class Task implements Runnable {
        String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            logger.info("处理" + this.name);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                10,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                (r, executors) -> {
                    //自定义饱和策略
                    //记录一下无法处理的任务
                    logger.info("无法处理的任务：" + r.toString());
                }) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                logger.info("开始执行任务:" + r.toString());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                logger.info("任务:" + r.toString() + "，执行完毕!");
            }

            @Override
            protected void terminated() {
                logger.info("关闭线程池!");
            }
        };
        for (int i = 0; i < 10; i++) {
            executor.execute(new Task("任务-" + i));
        }
        TimeUnit.SECONDS.sleep(1);
        executor.shutdown();
    }
}
