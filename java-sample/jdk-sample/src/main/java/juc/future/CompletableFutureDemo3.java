package juc.future;

import jdk.util.Threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            Threads.silentSleep(5, TimeUnit.SECONDS);
            return 5;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            Threads.silentSleep(3, TimeUnit.SECONDS);
            return 3;
        }), f -> f * 10).whenComplete((r, tx) -> System.out.println(r));

        future.get();
    }
}
