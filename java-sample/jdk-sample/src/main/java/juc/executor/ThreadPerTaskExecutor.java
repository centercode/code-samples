package juc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

/**
 * @see java.util.concurrent.Executor
 */
public class ThreadPerTaskExecutor implements Executor {

    private static Logger logger = LoggerFactory.getLogger(ThreadPerTaskExecutor.class);

    @Override
    public void execute(Runnable r) {
        logger.info("new Thread");
        new Thread(r).start();
    }
}
