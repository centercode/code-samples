package collection.queue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class LinkedTransferQueueDemo {

    public static void main(String[] args) throws Exception {
        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                while (true) {
                    System.out.println("thread");
                    TimeUnit.SECONDS.sleep(1);
//                                      queue.transfer(3);
//                                      System.out.println(queue.tryTransfer(3));

                    System.out.println(queue.hasWaitingConsumer());
                    System.out.println(queue.getWaitingConsumerCount());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // 开启5个子线程获取元素
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
