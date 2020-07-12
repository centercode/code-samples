package jdk.juc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * scheduleWithFixedDelay:固定的间隔执行任务
 */
public class ExecutorDemo3 {
    private static Logger logger = LoggerFactory.getLogger(ExecutorDemo3.class);

    public static void main(String[] args) {
        logger.info("Main start");
        //任务执行计数器
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            int currCount = count.getAndIncrement();
            logger.info(">>>第" + currCount + "次" + "开始执行");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("<<<第" + currCount + "次" + "执行结束");
        }, 1, 3, TimeUnit.SECONDS);
    }
}
