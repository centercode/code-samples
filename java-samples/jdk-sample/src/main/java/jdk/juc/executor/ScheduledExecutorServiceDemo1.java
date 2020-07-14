package jdk.juc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * schedule:延迟执行任务1次
 */
public class ScheduledExecutorServiceDemo1 {

    private static Logger logger = LoggerFactory.getLogger(ScheduledExecutorServiceDemo1.class);

    public static void main(String[] args) {
        logger.info("Main start");
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.schedule(() -> {
            logger.info("开始执行");
            //模拟任务耗时
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("执行结束");
        }, 2, TimeUnit.SECONDS);
    }
}
