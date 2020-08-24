package jdk.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;

public class StopDemo {

  private static Logger log = LoggerFactory.getLogger(StopDemo.class);

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new Thread(() -> {
      log.info("start");
      boolean flag = true;
      while (flag) {
      }
      log.info("end");
    });
    thread1.setName("thread1");
    thread1.start();
    //当前线程休眠1秒
    TimeUnit.SECONDS.sleep(1);
    //关闭线程thread1
    thread1.stop();
    //输出线程thread1的状态
    log.info(thread1.getState().toString());
    //当前线程休眠1秒
    TimeUnit.SECONDS.sleep(1);
    //输出线程thread1的状态
    log.info(thread1.getState().toString());
  }
}
