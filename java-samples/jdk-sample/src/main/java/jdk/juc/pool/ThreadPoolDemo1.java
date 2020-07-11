package jdk.juc.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo1 {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolDemo1.class);

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
            5,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int j = i;
            String taskName = "任务" + j;
            executor.execute(() -> {
                //模拟任务内部处理耗时
                try {
                    TimeUnit.SECONDS.sleep(j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info(taskName + "处理完毕");
            });
        }
        logger.info("Closing Pool...");
        //关闭线程池
        executor.shutdown();
    }
}
