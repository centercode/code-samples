package jdk.thread;

import java.util.concurrent.TimeUnit;

public class BreakLoopDemo1 {

    public static class T extends Thread {

        private volatile boolean exit = false;

        @Override
        public void run() {
            while (true) {
                //循环处理业务
                if (exit) {
                    break;
                }
            }
        }

        public void setExit() {
            exit = true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        TimeUnit.SECONDS.sleep(3);
        t.setExit();
    }
}