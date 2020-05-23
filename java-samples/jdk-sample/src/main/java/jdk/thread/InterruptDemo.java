package jdk.thread;

import java.util.concurrent.TimeUnit;

public class InterruptDemo {
  static volatile boolean isStop = false;

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 =
        interruptSleep();
    //    exitByInterrupt();
    //    exitByVariable();
    thread1.setName("thread1");
    thread1.start();
    System.out.println(isInterrupted(thread1));
    TimeUnit.SECONDS.sleep(1);
    thread1.interrupt();
    System.out.println(isInterrupted(thread1));
  }

  private static String isInterrupted(Thread t) {
    return t.getName() + ":" + t.isInterrupted();
  }

  private static Thread interruptSleep() {
    return new Thread() {
      @Override
      public void run() {
        while (true) {
          //休眠100秒
          try {
            TimeUnit.SECONDS.sleep(100);
          } catch (InterruptedException e) {
            this.interrupt();
            e.printStackTrace();
          }
          //sleep方法由于中断而抛出异常之后，线程的中断标志会被清除（置为false）
          System.out.println("我要退出了!" + this.isInterrupted());
          break;
        }
      }
    };
  }

  private static Thread exitByInterrupt() {
    return new Thread() {
      @Override
      public void run() {
        while (true) {
          if (this.isInterrupted()) {
            System.out.println("我要退出了!");
            break;
          }
        }
      }
    };
  }

  private static Thread exitByVariable() {
    return new Thread(() -> {
      while (true) {
        if (isStop) {
          System.out.println("我要退出了!");
          break;
        }
      }
    });
  }
}
