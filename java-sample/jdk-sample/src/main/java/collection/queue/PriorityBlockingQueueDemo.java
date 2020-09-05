package collection.queue;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(5, (o1, o2) -> o2 - o1);
        queue.add(10);
        queue.add(2);
        queue.add(5);

        while (true) {
            System.out.println(queue.take());
        }
    }
}
