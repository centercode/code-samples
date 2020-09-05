package juc.executor;

import jdk.util.Threads;
import juc.executor.SerialExecutor;
import juc.executor.ThreadPerTaskExecutor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class DirectExecutorTest {

    private static Logger logger = LoggerFactory.getLogger(DirectExecutorTest.class);

    Executor executor =
//            new DirectExecutor();
//            new ThreadPerTaskExecutor();
            new SerialExecutor(new ThreadPerTaskExecutor());

    @Test
    public void testExecute() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.execute(() -> {
                logger.info("NO." + finalI + " is Running...");
                Threads.silentSleep(2, TimeUnit.SECONDS);
            });
        }
        Threads.silentSleep(25, TimeUnit.SECONDS);
    }
}