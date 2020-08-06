package jdk.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class ThreadLocalDemo3 {

    private static Logger logger = LoggerFactory.getLogger(ThreadLocalDemo3.class);

    //创建一个操作Thread中存放请求任务追踪id口袋的对象
    static ThreadLocal<String> traceIds = new InheritableThreadLocal<>();

    static AtomicInteger counter = new AtomicInteger(0);

    static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            3, 3,
            60, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(),
            r -> {
                Thread thread = new Thread(r);
                thread.setName(String.format("thread-%02d", counter.getAndIncrement()));
                return thread;
            }
    );

    public static void main(String[] args) {
        //需要插入的数据
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            dataList.add(String.valueOf(i));
        }
        //模拟5个请求
        for (int i = 0; i < 5; i++) {
            String traceId = String.valueOf(i);
            pool.execute(() -> {
                //把traceId放入口袋中
                traceIds.set(traceId);
                try {
                    controller(dataList);
                } finally {
                    //将tranceId从口袋中移除
                    traceIds.remove();
                }
            });
        }
        pool.shutdown();
    }

    //模拟controller
    public static void controller(List<String> dataList) {
        log("接受请求");
        service(dataList);
    }

    //模拟service
    public static void service(List<String> dataList) {
        log("执行业务");
//        dao(dataList);
        inheritableDao(dataList);
    }

    //模拟dao
    public static void dao(List<String> dataList) {
        log("执行数据库操作");
        //模拟插入数据
        for (String s : dataList) {
            log("插入数据" + s + "成功");
        }
    }


    //模拟dao
    public static void inheritableDao(List<String> dataList) {
        CountDownLatch countDownLatch = new CountDownLatch(dataList.size());
        log("执行数据库操作");
        String threadName = Thread.currentThread().getName();
        //模拟插入数据
        for (String s : dataList) {
            new Thread(() -> {
                try {
                    //模拟数据库操作耗时100毫秒
                    TimeUnit.MILLISECONDS.sleep(100);
                    log("插入数据" + s + "成功,主线程：" + threadName);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        //等待上面的dataList处理完毕
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //记录日志
    public static void log(String msg) {
        String traceId = traceIds.get();
        logger.info("[traceId:" + traceId + "]:" + msg);
    }
}