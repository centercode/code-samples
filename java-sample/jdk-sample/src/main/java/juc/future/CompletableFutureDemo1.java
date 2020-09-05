package juc.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo1 {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(
                () -> new Random().nextInt(10)
        ).thenApply(n -> n * 10).thenApply(n -> {
            if (5 < n) {
                System.out.println("Success number: " + n);
                return "中奖了";
            } else {
                throw new IllegalStateException("Wrong number: " + n);
            }
        })//.thenAccept(s -> System.out.println("###" + s))
                .whenComplete((s, t) -> {
                    System.out.println(t == null);
                    System.out.println("CCTV1: 恭喜" + s);
                }).whenComplete((s, t) -> {
            System.out.println("CCTV2: 恭喜" + s);
        }).exceptionally(t -> "出错了");
    }
}
