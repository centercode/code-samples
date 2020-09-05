package thread;

import java.util.concurrent.TimeUnit;

public class BreakLoopDemo3 {

    public static class T extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("Loop...");
                //循环处理业务
                //下面模拟阻塞代码
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.interrupt();
                }
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