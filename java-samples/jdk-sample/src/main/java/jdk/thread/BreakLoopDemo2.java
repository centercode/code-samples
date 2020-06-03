package jdk.thread;

import java.util.concurrent.TimeUnit;


public class BreakLoopDemo2 {

    public static class T extends Thread {

        @Override
        public void run() {
            while (true) {
                //循环处理业务
                if (this.isInterrupted()) {
                    break;
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        TimeUnit.SECONDS.sleep(3);
        t.interrupt();
    }
}