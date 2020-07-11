package jdk.juc.pool;

import jdk.util.Threads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 生命周期
 */
public class ThreadPoolDemo7 {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolDemo7.class);

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new SynchronousQueue<>());
        executor.execute(() -> {
            logger.info("Hello...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("World!");
        });
        executor.shutdown();
        executor.shutdownNow();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("is isShutdown:" + executor.isShutdown());
        logger.info("is Terminating:" + executor.isTerminating());
        Threads.silentSleep(2, TimeUnit.SECONDS);
        logger.info("is Terminated:" + executor.isTerminated());
    }
}
