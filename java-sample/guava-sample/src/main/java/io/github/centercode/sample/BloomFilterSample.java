package io.github.centercode.sample;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * https://mp.weixin.qq.com/s/Y7OJ0ntjU0pumWuwFoY8mQ
 * 从容器的角度来说：
 * 如果布隆过滤器判断元素在集合中存在，不一定存在
 * 如果布隆过滤器判断不存在，一定不存在
 * <p>
 * 从元素的角度来说：
 * 如果元素实际存在，布隆过滤器一定判断存在
 * 如果元素实际不存在，布隆过滤器可能判断存在
 */
public class BloomFilterSample {

    //插入多少数据
    private static final int amount = 1_000_000;

    public static void main(String[] args) {
        //初始化一个存储string数据的布隆过滤器,默认误判率fpp是0.03
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), amount, 0.02);
        //用于存放所有实际存在的key，用于是否存在
        Set<String> sets = new HashSet<>(amount);
        //用于存放所有实际存在的key，用于取出
        List<String> lists = new ArrayList<>(amount);
        //插入随机字符串
        for (int i = 0; i < amount; i++) {
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            sets.add(uuid);
            lists.add(uuid);
        }

        int TP = 0;
        int FP = 0;
        int TN = 0;
        int FN = 0;

        for (int i = 0; i < 10_000; i++) {
            // 0-10000之间，可以被100整除的数有100个（100的倍数）
            String data = i % 100 == 0 ? lists.get(i / 100) : UUID.randomUUID().toString();
            //这里用了might,看上去不是很自信，所以如果布隆过滤器判断存在了,我们还要去sets中实锤
            if (bf.mightContain(data)) {
                if (sets.contains(data)) {
                    TP++;
                } else {
                    FP++;
                }
            } else if (sets.contains(data)) {
                FN++;
            } else {
                TN++;
            }
        }
        System.out.println("FP:" + FP);
        System.out.println("TP:" + TP);
        System.out.println("FN:" + FN);
        System.out.println("TN:" + TN);

        if (FN + TP != 100) throw new AssertionError("100");
        if (FP + TN != 9900) throw new AssertionError("9900");

        int P = TP + FP;
        int N = TN + FN;
        int T = TP + FN;
        int F = TN + FP;
        //精确度
        BigDecimal precision = divide(TP, P);
        //召回率
        BigDecimal recall = divide(TP, T);
        //真阳性率
        BigDecimal TPR = divide(TP, T);
        //假阳性率
        BigDecimal FPR = divide(FP, F);
        //准确率
        BigDecimal acc = divide((TP + TN), (P + N));
        System.out.println("FPR:" + FPR);
    }

    private static BigDecimal divide(int a, int b) {
        return new BigDecimal(a).divide(new BigDecimal(b), 2, RoundingMode.HALF_UP);
    }
}
