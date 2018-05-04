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

    private static final int TIMES = 10000;

    LuckyMoneyService service = new LuckyMoneyService();

    @Test
    public void testRushLuckyMoney() throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(TIMES);
        double money = 88;
        int n = 100;

        for (int i = 0; i < n; i++) {
//            final long uid = i;
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    List<String> gifts = service.randomAllocateMoney(money, n);
                    BigDecimal sum = service.sumGifts(gifts);
                    System.out.println("sum:" + sum + ", TIMES:" + n);
                    Assert.isTrue(sum.compareTo(BigDecimal.valueOf(money)) == 0);
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(300, TimeUnit.SECONDS);
    }
}
