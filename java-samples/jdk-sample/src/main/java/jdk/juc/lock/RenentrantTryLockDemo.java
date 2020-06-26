package jdk.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RenentrantTryLockDemo {

    private static final ReentrantLock lock = new ReentrantLock(false);

    public static class T extends Thread {

        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                System.out.println(System.currentTimeMillis() + ":" + this.getName() + "开始获取锁!");
                //获取锁超时时间设置为3秒，3秒内是否能否获取锁都会返回
                if (lock.tryLock()) {
                    System.out.println(System.currentTimeMillis() + ":" + this.getName() + "获取到了锁!");
                    //获取到锁之后，休眠5秒
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    System.out.println(System.currentTimeMillis() + ":" + this.getName() + "未能获取到锁!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        T t1 = new T("t1");
        T t2 = new T("t2");

        t1.start();
        t2.start();
    }
}
