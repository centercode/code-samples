package juc.future;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CompletableFutureSampleTest {

    /**
     * see: https://stackoverflow.com/questions/34004802
     */
    @Test
    public void testAllOf() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> stage1 = CompletableFuture.supplyAsync(() -> action(5), executor);
        CompletableFuture<Integer> stage2 = CompletableFuture.supplyAsync(() -> action(1), executor);
        CompletableFuture<Integer> stage3 = CompletableFuture.supplyAsync(() -> action(3), executor);
        CompletableFuture<Void> future = CompletableFuture.allOf(stage1, stage2, stage3)
                .thenAcceptAsync(res -> {
                    stage1.join();
                    stage2.join();
                    stage3.join();
                    System.out.println("all stages joined.");
                }, executor);
        long start = System.nanoTime();
        future.get();
        double elapsed = (System.nanoTime() - start) * 1E-6;
        Assert.assertEquals(elapsed, 5000, 5);
        System.out.println("elapsed time:" + elapsed + "ms");
    }

    @Test
    public void testCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> stage1 = CompletableFuture.supplyAsync(() -> action(5), executor);
        CompletableFuture<Integer> stage2 = CompletableFuture.supplyAsync(() -> action(1), executor);
        CompletableFuture<Integer> stage3 = CompletableFuture.supplyAsync(() -> action(3), executor);
        CompletableFuture<Result> future = CompletableFuture.completedFuture(new Result())
                .thenCombineAsync(stage1, (res, num) -> res.with(0, num), executor)
                .thenCombineAsync(stage2, (res, num) -> res.with(1, num), executor)
                .thenCombineAsync(stage3, (res, num) -> res.with(2, num), executor);
        long start = System.nanoTime();
        future.get();
        double elapsed = (System.nanoTime() - start) * 1E-6;
        Assert.assertEquals(elapsed, 5000, 5);
        System.out.println("elapsed time:" + elapsed + "ms");
    }

    static class Result {

        private final int[] nums = new int[3];

        Result with(int i, int num) {
            this.nums[i] = num;
            return this;
        }
    }

    private final ExecutorService executor = Executors.newCachedThreadPool(new ThreadFactory() {

        final AtomicInteger sequence = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable run) {
            Thread t = new Thread(run);
            t.setName("my-thread-pool-" + sequence.getAndIncrement());
            return t;
        }
    });

    private int action(int blockSeconds) {
        try {
            System.out.println("Run on thread '" + Thread.currentThread().getName() + "' with " + blockSeconds + "s blocking.");
            TimeUnit.SECONDS.sleep(blockSeconds);
            return blockSeconds;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}