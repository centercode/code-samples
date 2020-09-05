package juc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

/**
 * @see java.util.concurrent.Executor
 */
public class SerialExecutor implements Executor {

    private static Logger logger = LoggerFactory.getLogger(SerialExecutor.class);

    final Queue<Runnable> tasks = new ArrayDeque<>();
    final Executor executor;
    Runnable active;

    public SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    public synchronized void execute(final Runnable r) {
        tasks.offer(() -> {
            try {
                r.run();
            } finally {
                scheduleNext();
            }
        });
        if (active == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        logger.info("poll task...");
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }
}