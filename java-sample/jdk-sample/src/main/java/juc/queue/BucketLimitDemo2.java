package juc.queue;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *
 */
public class BucketLimitDemo2 {

    private static Logger logger = LoggerFactory.getLogger(BucketLimitDemo2.class);

    public static void main(String[] args) {
        BucketLimit bucketLimit = new BucketLimit(10, 60, TimeUnit.MINUTES);
        for (int i = 0; i < 15; i++) {
            new Thread(() -> {
                boolean acquire = bucketLimit.flowInto();
                logger.info("acquire: " + acquire);
//                Threads.silentSleep(1, TimeUnit.SECONDS);
            }).start();
        }
    }

    public static class BucketLimit {
        //漏桶流出的任务时间间隔（纳秒）
        private long intervalNanos;

        private BlockingQueue<Thread> queue;

        private BucketLimit(int capacity, int rate, TimeUnit rateUnit) {
            if (capacity < 0 || rate < 0) {
                throw new IllegalArgumentException("capacity、flowRate必须大于0！");
            }
            this.queue = new ArrayBlockingQueue<>(capacity);
            //漏桶流出的任务时间间隔（纳秒）
            this.intervalNanos = rateUnit.toNanos(1) / rate;
            this.leak();
        }

        //漏桶线程
        public void leak() {
            Thread thread = new Thread(() -> {
                while (true) {
                    Thread node = this.queue.poll();
                    if (Objects.nonNull(node)) {
                        //唤醒任务线程
                        LockSupport.unpark(node);
                    }
                    //休眠flowRateNanosTime
                    LockSupport.parkNanos(this.intervalNanos);
                }
            });
            thread.start();
        }

        //当前线程加入漏桶，
        // 返回false：表示漏桶已满；
        // 返回true ：表示被漏桶限流成功，可以继续处理任务
        public boolean flowInto() {
            if (this.queue.offer(Thread.currentThread())) {
//                logger.info("park");
                LockSupport.park();
                return true;
            }
            return false;
        }
    }

}
