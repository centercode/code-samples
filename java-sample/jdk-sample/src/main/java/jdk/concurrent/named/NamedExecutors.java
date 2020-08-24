package jdk.concurrent.named;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NamedExecutors {

    public static ExecutorService newFixedThreadPool(int nThreads, String poolName) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new NamedThreadFactory(poolName));
    }

    public static ExecutorService newCachedThreadPool(String poolName) {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new NamedThreadFactory(poolName));
    }

    public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, String poolName) {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                new NamedThreadFactory(poolName));
    }
}
