package jdk.juc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask类
 */
public class FutureTaskDemo9 {
    private static Logger logger = LoggerFactory.getLogger(FutureTaskDemo9.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            logger.info("start!");
            TimeUnit.SECONDS.sleep(5);
            logger.info("end!");
            return 10;
        });
        logger.info("");
        new Thread(futureTask).start();
        logger.info("");
        logger.info("结果:" + futureTask.get());
    }
}
