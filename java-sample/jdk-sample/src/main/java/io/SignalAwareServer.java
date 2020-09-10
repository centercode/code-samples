package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.concurrent.CountDownLatch;

/**
 * kill -31 $pid [on MacOS]
 */
public class SignalAwareServer implements SignalHandler {

    private static Logger logger = LoggerFactory.getLogger(SignalAwareServer.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        SignalAwareServer server = new SignalAwareServer();
        server.start();
    }

    public void start() throws InterruptedException {
        // 捕捉信号
        Signal.handle(new Signal("USR2"), this);
        latch.await();
    }

    private void stop() {
        latch.countDown();
        String threadName = Thread.currentThread().getName();
        logger.info("threadName:" + threadName + ", Stopped successfully.");
    }

    @Override
    public void handle(Signal sn) {
        logger.info("Received Signal: [" + sn.getNumber() + ":" + sn.getName() + "]");
        stop();
    }
}