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

    LuckyMoneyService service1 = new LuckyMoneyService();
//     service2 = new LuckyMoneyService2();

    @Test
    public void testRandomAllocateMoney1() throws InterruptedException {
        final int THS = 8;
        final int TIMES = 100000;
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
                        gifts = service1.randomAllocateMoney(money, n);
                        sum = service1.sumGifts(gifts);
//                        System.out.println("sum:" + sum + ", THS:" + n);
                        Assert.isTrue(sum.compareTo(BigDecimal.valueOf(money)) == 0);
                    }
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(10, TimeUnit.MINUTES);
    }

    @Test
    public void testRandomAllocateMoney2_10000_10() throws InterruptedException {
        randomAllocateMoney2(8, 100000, 10000, 10);
    }

    @Test
    public void testRandomAllocateMoney2_1000_100() throws InterruptedException {
        randomAllocateMoney2(8, 100000, 100, 10);
    }

    // times:800000,total cost times:3747
    @Test
    public void testRandomAllocateMoney2_10000_100() throws InterruptedException {
        randomAllocateMoney2(8, 100000, 100, 10);
    }

    // times:800000,total cost times:96
    @Test
    public void testRandomAllocateMoney2_10_10() throws InterruptedException {
        randomAllocateMoney2(8, 100000, 10, 10);
    }

    // times:800000,total cost times:114
    @Test
    public void testRandomAllocateMoney2_10000_5() throws InterruptedException {
        randomAllocateMoney2(8, 100000, 100000, 5);
    }

    @Test
    public void testRandomAllocateMoneySingle() {
        List<Integer> gifts = LuckyMoneyService2.randomAllocateMoney(100000, 5);
        System.out.println(gifts);
        Integer sum = LuckyMoneyService2.sumGifts(gifts);
        System.out.println(sum);
    }

    public void randomAllocateMoney2(int th, int times, int money, int n) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService exec = Executors.newFixedThreadPool(th);
        for (int i = 0; i < th; i++) {
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    List<Integer> gifts;
                    Integer sum;
                    String thName = Thread.currentThread().getName();
                    for (int j = 0; j < times; j++) {
//                        System.out.println("curThName:" + thName + ",curTimes:" + j);
                        gifts = LuckyMoneyService2.randomAllocateMoney(money, n);
                        sum = LuckyMoneyService2.sumGifts(gifts);
//                        System.out.println("sum:" + sum + ", THS:" + n);
                        Assert.isTrue(sum == money);
                    }
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(10, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println("times:" + times * th + ",total cost times:" + (end - start));
    }
}
