package juc.atomic;

import util.Threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampDemo {

    private interface Adder {
        boolean incr(int expect, int update, int version);
    }

    private static class AtomicAdder implements Adder {
        private AtomicInteger atomic = new AtomicInteger(100);

        @Override
        public boolean incr(int expect, int update, int version) {
            return atomic.compareAndSet(expect, update);
        }
    }

    private static class StampedAtomicAdder implements Adder {
        private AtomicStampedReference<Integer> ref = new AtomicStampedReference<>(100, 0);

        @Override
        public boolean incr(int expect, int update, int stamp) {
            return ref.compareAndSet(expect, update, stamp, stamp + 1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Adder atomicAdder = new AtomicAdder();
        execute(atomicAdder);
        Adder atomicAdder2 = new StampedAtomicAdder();
        execute(atomicAdder2);
    }

    private static void execute(Adder adder) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            adder.incr(100, 101, 0);
            adder.incr(101, 100, 1);
        });
        Thread t2 = new Thread(() -> {
            Threads.silentSleep(1, TimeUnit.SECONDS);
            boolean success = adder.incr(100, 101, 0);
            System.out.println(success);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
