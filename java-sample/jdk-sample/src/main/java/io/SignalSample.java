package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.concurrent.CountDownLatch;

/**
 * 类似责任链模式需要传递给下一个handler
 * 同一个Signal的多个Handler按尾插法组成一个链表
 */
public class SignalSample {

    private static Logger logger = LoggerFactory.getLogger(SignalSample.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private SignalHandler oldHandler1;
    private SignalHandler oldHandler2;
    private SignalHandler oldHandler3;

    public static void main(String[] args) throws InterruptedException {
        SignalSample server = new SignalSample();
        server.start();
    }

    public void start() throws InterruptedException {
        /*
         * Registers a signal handler.
         *
         * @param sig a signal
         * @param handler the handler to be registered with the given signal.
         * @result the old handler
         * @exception IllegalArgumentException the signal is in use by the VM
         * @see sun.misc.Signal#raise(Signal sig)
         * @see sun.misc.SignalHandler
         * @see sun.misc.SignalHandler#SIG_DFL
         * @see sun.misc.SignalHandler#SIG_IGN
         */
        oldHandler1 = Signal.handle(new Signal("INT"), sn -> {
            logger.info("Handler1 Received");
            oldHandler1.handle(sn);
        });
        new Thread(() -> oldHandler2 = Signal.handle(new Signal("INT"), sig -> {
            logger.info("Handler2 Received");
            oldHandler2.handle(sig);
        })).start();
        new Thread(() -> oldHandler3 = Signal.handle(new Signal("INT"), sig -> {
            logger.info("Handler3 Received");
            oldHandler3.handle(sig);
        })).start();
        logger.info("Server started");
        latch.await();
    }
}