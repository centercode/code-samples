package jdk.juc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 获取异步任务执行结果
 */
public class ExecutorServiceDemo6 {
    private static Logger logger = LoggerFactory.getLogger(ExecutorServiceDemo6.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> result = executorService.submit(() -> {
            logger.info("start!");
            TimeUnit.SECONDS.sleep(5);
            logger.info("end!");
            return 10;
        });
        logger.info(",结果：" + result.get());
    }
}
