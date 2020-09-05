package collection.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                2,
                10000,
                TimeUnit.DAYS,
                //这里如果指定了固定的长度就表示是有界队列了
                new LinkedBlockingDeque<>(2)
        );
        List<Future<?>> futures = new ArrayList<>();
        for (int a = 0; a < 1000; a++) {
            futures.add(executor.submit(() -> {
                System.out.println("thread start : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread end : " + Thread.currentThread().getName());
            }));
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}