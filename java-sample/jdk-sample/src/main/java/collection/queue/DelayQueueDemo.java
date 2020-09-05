package collection.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<TestDelayed> queue = new DelayQueue<>();

        long time = System.currentTimeMillis() + 10000;

        queue.put(new TestDelayed(1, time + 500));
        queue.put(new TestDelayed(2, time + 300));
        queue.put(new TestDelayed(3, time + 100));
        queue.put(new TestDelayed(4, time + 200));
        queue.put(new TestDelayed(5, time + 400));

        while (true) {
            TestDelayed delayed = queue.take();
            System.out.println(delayed.orderNumber);
        }
    }

    // 自定义Delayed类
    private static class TestDelayed implements Delayed {
        // 订单号
        int orderNumber;
        // 超时时间
        long expire;

        TestDelayed(int orderNumber, long expire) {
            this.orderNumber = orderNumber;
            this.expire = expire;
        }

        // 超时时间减去当前时间，如果小于0就表示时间到了
        @Override
        public long getDelay(TimeUnit unit) {
            return expire - System.currentTimeMillis();
        }

        // 设置对比规则
        @Override
        public int compareTo(Delayed o) {
            long t1 = this.getDelay(TimeUnit.MILLISECONDS);
            long t2 = o.getDelay(TimeUnit.MILLISECONDS);
            return Long.compare(t1, t2);
        }
    }
}
