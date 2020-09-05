package juc.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义创建线程的工厂
 */
public class ThreadPoolDemo4 {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolDemo4.class);

    static AtomicInteger threadNum = new AtomicInteger(1);

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                5,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("自定义线程-" + threadNum.getAndIncrement());
                    return thread;
                });
        for (int i = 0; i < 5; i++) {
            String taskName = "任务-" + i;
            executor.execute(() -> {
                logger.info("处理" + taskName);
            });
        }
        executor.shutdown();
    }
}