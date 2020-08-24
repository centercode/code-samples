package jdk.concurrent.named;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 参考 https://blog.csdn.net/yueloveme/article/details/85048563
 */
public class NamedThreadFactory implements ThreadFactory {

    private final ThreadGroup group;
    private final String namePrefix;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public NamedThreadFactory(String poolName) {
        namePrefix = "Pool-" + poolName + "-";
        SecurityManager s = System.getSecurityManager();
        group = (s != null)
                ? s.getThreadGroup()
                : Thread.currentThread().getThreadGroup();
    }

    public Thread newThread(Runnable r) {
        String name = namePrefix + threadNumber.getAndIncrement();
        Thread t = new Thread(group, r, name);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
