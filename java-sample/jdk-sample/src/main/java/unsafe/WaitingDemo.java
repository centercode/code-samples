package unsafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class WaitingDemo {

    interface WaitService {
        void await();

        void awaken(Thread t);
    }

    private static Logger logger = LoggerFactory.getLogger(WaitingDemo.class);

    public static void main(String[] args) throws InterruptedException {
        WaitService service =
                new ParkWait();
//                new ConditionWait();
//                new ObjectWait();
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
            logger.info("0>>> start await...");
            service.await();
            logger.info("0<<< end await");
        });
        t.setName("WaitingDemoThread1");
        t.start();
        TimeUnit.SECONDS.sleep(3);
        t.interrupt();
        logger.info("1>>> start awaken...");
        service.awaken(t);
        logger.info("1<<< finish awaken");
    }

    private static class ParkWait implements WaitService {

        private Object blocker = new Object();

        @Override
        public void await() {
            LockSupport.park(blocker);
        }

        @Override
        public void awaken(Thread t) {
            LockSupport.unpark(t);
        }
    }

    private static class ConditionWait implements WaitService {

        private ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        @Override
        public void await() {
            try {
                lock.lock();
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void awaken(Thread t) {
            try {
                lock.lock();
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class ObjectWait implements WaitService {

        private final Object lock = new Object();

        @Override
        public void await() {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void awaken(Thread t) {
            synchronized (lock) {
                lock.notifyAll();
            }
        }
    }

}
