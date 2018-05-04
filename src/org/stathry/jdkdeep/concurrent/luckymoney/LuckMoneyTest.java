package org.stathry.jdkdeep.concurrent.luckymoney;

import org.junit.Test;
import org.stathry.jdkdeep.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 2018/5/3
 */
public class LuckMoneyTest {

    private static final int THS = 8;
    private static final int TIMES = 100000;

    LuckyMoneyService service = new LuckyMoneyService();

    @Test
    public void testRandomAllocateMoney() throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(THS);
//        double money = 1000;
//        int n = 10;

// double money = 100;
//        int n = 100;

 double money = 10;
        int n = 100;

        for (int i = 0; i < THS; i++) {
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    List<BigDecimal> gifts;
                    BigDecimal sum;
                    String thName = Thread.currentThread().getName();
                    for (int j = 0; j < TIMES; j++) {
                        System.out.println("curThName:" + thName + ",curTimes:" + j);
                        gifts = service.randomAllocateMoney(money, n);
                        sum = service.sumGifts(gifts);
//                        System.out.println("sum:" + sum + ", THS:" + n);
                        Assert.isTrue(sum.compareTo(BigDecimal.valueOf(money)) == 0);
                    }
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(10, TimeUnit.MINUTES);
    }
}
