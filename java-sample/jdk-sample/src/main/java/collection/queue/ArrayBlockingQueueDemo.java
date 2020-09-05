package collection.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo {

    private static ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) throws Exception {
        System.out.println(arrayBlockingQueue.size());
        new Thread(() -> {
            try {
                addQueue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        arrayBlockingQueue();
    }

    private static void arrayBlockingQueue() throws Exception {
        arrayBlockingQueue.put(1);
        arrayBlockingQueue.put(2);
        arrayBlockingQueue.put(3);

        while (true) {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(arrayBlockingQueue.size() + "-" + arrayBlockingQueue.take());
        }
    }

    private static void addQueue() throws Exception {
        while (true) {
            TimeUnit.SECONDS.sleep(2);
            arrayBlockingQueue.put(new Random().nextInt(100));
        }
    }
}
