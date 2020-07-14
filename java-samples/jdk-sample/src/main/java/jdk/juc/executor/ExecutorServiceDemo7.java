package jdk.juc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * cancel方法示例
 */
public class ExecutorServiceDemo7 {
    private static Logger logger = LoggerFactory.getLogger(ExecutorServiceDemo7.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(() -> {
            logger.info(",start!");
            TimeUnit.SECONDS.sleep(5);
            logger.info(",end!");
            return 10;
        });

        executorService.shutdown();

        TimeUnit.SECONDS.sleep(1);
        result.cancel(false);
        logger.info(String.valueOf(result.isCancelled()));
        logger.info(String.valueOf(result.isDone()));

        TimeUnit.SECONDS.sleep(5);
        logger.info(",结果：" + result.get());
        executorService.shutdown();
    }
}
