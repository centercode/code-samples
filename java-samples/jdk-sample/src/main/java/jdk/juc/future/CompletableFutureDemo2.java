package jdk.juc.future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo2 {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(
                () -> 2
        ).thenCompose(n -> CompletableFuture.supplyAsync(
                () -> n * 10
        )).thenAcceptBoth(CompletableFuture.supplyAsync(
                () -> "Result"
        ), (n, s) -> System.out.println(s + ":" + n));
    }
}
