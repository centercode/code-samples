package jdk.juc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 *
 */
public class ExecutorCompletionServiceDemo14 {

    private static Logger logger = LoggerFactory.getLogger(ExecutorCompletionServiceDemo14.class);

    static class GoodsModel {
        //商品名称
        String name;
        //购物开始时间
        long startime;
        //送到的时间
        long endtime;

        public GoodsModel(String name, long startime, long endtime) {
            this.name = name;
            this.startime = startime;
            this.endtime = endtime;
        }

        @Override
        public String toString() {
            return name + " 耗时:" + (this.endtime - this.startime);
        }
    }

    /**
     * 将商品搬上楼
     */
    static void moveUp(GoodsModel goodsModel) throws InterruptedException {
        //休眠5秒，模拟搬上楼耗时
        TimeUnit.SECONDS.sleep(5);
        logger.info("将商品搬上楼，商品信息:" + goodsModel);
    }

    /**
     * 模拟下单
     */
    static Callable<GoodsModel> buyGoods(String name, long costTime) {
        return () -> {
            long startTime = System.currentTimeMillis();
            logger.info("购买" + name + "下单!");
            //模拟送货耗时
            try {
                TimeUnit.SECONDS.sleep(costTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            logger.info(name + "送到了!");
            return new GoodsModel(name, startTime, endTime);
        };
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long st = System.currentTimeMillis();
        logger.info("开始购物!");
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //创建ExecutorCompletionService对象
        ExecutorCompletionService<GoodsModel> ecs = new ExecutorCompletionService<>(executor);
        //异步下单购买冰箱
        ecs.submit(buyGoods("冰箱", 5));
        //异步下单购买洗衣机
        ecs.submit(buyGoods("洗衣机", 2));
        executor.shutdown();

        //购买商品的数量
        int goodsCount = 2;
        for (int i = 0; i < goodsCount; i++) {
            //可以获取到最先到的商品
            GoodsModel goodsModel = ecs.take().get();
            //将最先到的商品送上楼
            moveUp(goodsModel);
        }
        long et = System.currentTimeMillis();
        logger.info("货物已送到家里咯，哈哈哈！");
        logger.info("总耗时:" + (et - st));
    }
}
