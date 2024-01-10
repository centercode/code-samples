package io.github.centercode.sample;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.lang.id.NanoId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class HuToolUUIDBenchmark {

    private static final Random RANDOM = new Random();

    private static final char[] LETTERS = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {
        Supplier<String> uuidByTimestamp = () -> String.valueOf(System.currentTimeMillis());
        Supplier<String> uuidByNanoTime = () -> String.valueOf(System.nanoTime());
        Supplier<String> uuidByLetters = () -> {
            int len = 13;
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++) {
                int n = RANDOM.nextInt(LETTERS.length);
                char c = LETTERS[n];
                sb.append(c);
            }
            return sb.toString();
        };
        Supplier<String> uuidByFastUUID = () -> UUID.fastUUID().toString(true);
        Supplier<String> uuidByRandomUUID = () -> UUID.randomUUID().toString();
        Supplier<String> uuidByJDKRandomUUID = () -> java.util.UUID.randomUUID().toString();
        Supplier<String> uuidByNanoId = () -> NanoId.randomNanoId();
        List<Supplier<String>> list = new ArrayList<>();
        list.add(uuidByTimestamp);    // 0
        list.add(uuidByNanoTime);     // 1
        list.add(uuidByLetters);      // 2
        list.add(uuidByFastUUID);     // 3
        list.add(uuidByRandomUUID);   // 4
        list.add(uuidByJDKRandomUUID);// 5
        list.add(uuidByNanoId);       // 6

        for (int i = 0; i < list.size(); i++) {
            Supplier<String> supplier = list.get(i);
            long elapsedNanos = benchmark(supplier, 10000);
            System.out.println("supplier" + i + " elapsed " + (elapsedNanos) + "ns, format: " + supplier.get());
        }
    }

    static long benchmark(Supplier<String> supplier, int count) {
        // warmup
        for (int i = 0; i < 3; i++) {
            supplier.get();
        }
        long s = System.nanoTime();
        for (int i = 0; i < count; i++) {
            supplier.get();
        }
        long e = System.nanoTime();
        return e - s;
    }
}
