package juc.atomic;

import jdk.util.Threads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 有一家蛋糕店，为了挽留客户，决定为贵宾卡客户一次性赠送20元，刺激客户充值和消费，但条件是，每一位客户只能被赠送一次
 */
public class AtomicReferenceDemo3 {

    private static Logger logger = LoggerFactory.getLogger(AtomicReferenceDemo3.class);

    //账户原始余额
    static int originalMoney = 19;
    //用于对账户余额做原子操作
    static AtomicReference<Integer> money = new AtomicReference<>(originalMoney);

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(AtomicReferenceDemo3::recharge).start();
        }
        new Thread(AtomicReferenceDemo3::consume).start();
    }

    /**
     * 模拟2个线程同时更新后台数据库，为用户充值
     */
    static void recharge() {
        for (int j = 0; j < 5; j++) {
            Integer m = money.get();
            if (originalMoney == m) {
                if (money.compareAndSet(m, m + 20)) {
                    logger.info("当前余额：" + m + "，小于20，充值20元成功，余额：" + money.get() + "元");
                }
            } else {
                logger.info("Ignore");
            }
            Threads.silentSleep(100, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * 模拟用户消费
     */
    static void consume() {
        for (int i = 0; i < 5; i++) {
            Integer m = money.get();
            if (m > 20) {
                if (money.compareAndSet(m, m - 20)) {
                    logger.info("当前余额：" + m + "，大于10，成功消费10元，余额：" + money.get() + "元");
                }
            } else {
                logger.info("Ignore");
            }
            Threads.silentSleep(50, TimeUnit.MILLISECONDS);
        }
    }

}