package jdk.juc.executor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * 异步执行一批任务，有一个完成立即返回，其他取消, 方式1:
 */
public class ExecutorServiceDemo16 {

    private static Logger logger = LoggerFactory.getLogger(ExecutorServiceDemo16.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> list = new ArrayList<>();
        int taskCount = 5;
        for (int i = taskCount; i > 0; i--) {
            int j = i * 2;
            String taskName = "任务" + i;
            list.add(() -> {
                TimeUnit.SECONDS.sleep(j);
                logger.info(taskName + "执行完毕!");
                return j;
            });
        }
        Integer r = executorService.invokeAny(list);
//        Integer r = invokeAny(executorService, list);
        logger.info("耗时:" + (System.currentTimeMillis() - start) + ",执行结果:" + r);
        executorService.shutdown();
    }


    public static <T> T invokeAny(Executor e, Collection<Callable<T>> solvers) throws InterruptedException, ExecutionException {
        CompletionService<T> ecs = new ExecutorCompletionService<>(e);
        List<Future<T>> futures = new ArrayList<>();
        for (Callable<T> s : solvers) {
            futures.add(ecs.submit(s));
        }
        try {
            for (int i = 0, n = solvers.size(); i < n; ++i) {
                T r = ecs.take().get();
                if (r != null) {
                    return r;
                }
            }
        } finally {
            for (Future<T> future : futures) {
                future.cancel(true);
            }
        }
        return null;
    }
}
