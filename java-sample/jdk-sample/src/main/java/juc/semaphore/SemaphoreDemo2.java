package juc.semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 示例2：获取许可之后不释放
 */
public class SemaphoreDemo2 {

    private static Logger logger = LoggerFactory.getLogger(SemaphoreDemo2.class);

    static Semaphore semaphore = new Semaphore(2);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                logger.info(getName() + ",获取许可!");
                TimeUnit.SECONDS.sleep(3);
                logger.info(getName() + ",运行结束!");
                logger.info(getName() + ",当前可用许可数量:" + semaphore.availablePermits());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new T("t-" + i).start();
        }
    }
}
