package juc.lock;

/**
 * 线程死锁演示
 */
public class DeadLock {

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread thread1 = new Thread(new Executor(obj1, obj2));
        Thread thread2 = new Thread(new Executor(obj2, obj1));
        thread1.setName("thread1");
        thread2.setName("thread2");
        thread1.start();
        thread2.start();
    }

    public static class Executor implements Runnable {
        Object obj1;
        Object obj2;

        public Executor(Object obj1, Object obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }

        @Override
        public void run() {
            try {
                synchronized (obj1) {
                    Thread.sleep(100);
                    synchronized (obj2) {
                        System.out.println("abc");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}