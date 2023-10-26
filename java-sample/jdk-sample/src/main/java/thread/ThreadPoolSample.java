package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现一个简单的线程池
 */
public class ThreadPoolSample {
    private final int coreSize;
    private final int maxSize;
    private final BlockingQueue<Runnable> taskQueue;
    private final List<Thread> running = new ArrayList<>();
    private final ReentrantLock runningLock = new ReentrantLock();

    public ThreadPoolSample(int coreSize, int maxSize, int queueSize) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = new LinkedBlockingQueue<>(queueSize);
    }

    public void submit(Runnable run) {
        Worker w = new Worker(run);
        runningLock.lock();
        try {
            // fill core pool size
            if (running.size() < coreSize) {
                running.add(w);
                w.start();
                return;
            }
        } finally {
            runningLock.unlock();
        }
        // fill taskQueue
        if (taskQueue.offer(w)) {
            return;
        }
        runningLock.lock();
        try {
            // fill until max pool size
            if (running.size() <= maxSize) {
                running.add(w);
                w.start();
                return;
            }
        } finally {
            runningLock.unlock();
        }
        // reject task if over max pool size
        throw new IllegalStateException();
    }

    public int getRunningSize() {
        runningLock.lock();
        try {
            return running.size();
        } finally {
            runningLock.unlock();
        }
    }

    class Worker extends Thread {

        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            if (task != null) {
                task.run();
                task = null;
            }
            while (true) {
                Runnable t = taskQueue.poll();
                if (t != null) {
                    t.run();
                }
            }
        }
    }

}
