package jdk.juc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时任务有异常会怎么样？
 */
public class ExecutorDemo4 {

    private static Logger logger = LoggerFactory.getLogger(ExecutorDemo4.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("Main start");
        //任务执行计数器
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            int currCount = count.getAndIncrement();
            logger.info("第" + currCount + "次" + "开始执行");
            logger.info(String.valueOf(10 / 0));
            logger.info("第" + currCount + "次" + "执行结束");
        }, 1, 1, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(5);
        logger.info(String.valueOf(scheduledFuture.isCancelled()));
        logger.info(String.valueOf(scheduledFuture.isDone()));
    }
}
