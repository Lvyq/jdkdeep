package org.stathry.jdkdeep.concurrent.luckymoney;

import org.junit.Test;
import org.stathry.jdkdeep.lang.inst.A;
import org.stathry.jdkdeep.util.Assert;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 随机红包测试
 *
 * @author dongdaiming
 * @date 2018/5/3
 */
public class RandomGiftTest {


    @Test
    public void testRandomGiftsSingle() {
        List<Integer> gifts = RandomGiftUtils.randomGifts(100, 10);
        System.out.println(gifts);
        Integer sum = RandomGiftUtils.sumGifts(gifts);
        System.out.println(sum);
    }

    @Test
    public void testRandomGiftsSingle_m10_n10() {
        int money = 10;
        int n = 10;
        int times = 100_10000;
        randomGifts(times, money, n);
    }

    @Test
    public void testRandomGiftsSingle_m100_n10() {
        int money = 100;
        int n = 10;
        int times = 100_10000;
        randomGifts(times, money, n);
    }

    @Test
    public void testRandomGiftsSingle_m1000_n10() {
        int money = 1000;
        int n = 10;
        int times = 100_10000;
        randomGifts(times, money, n);
    }

    @Test
    public void testRandomGiftsSingle_m10000_n10() {
        int money = 10000;
        int n = 10;
        int times = 100_10000;
        randomGifts(times, money, n);
    }

    @Test
    public void testRandomGiftsSingle_m10_0000_n10() {
        int money = 10_0000;
        int n = 10;
        int times = 100_10000;
        randomGifts(times, money, n);
    }

    @Test
    public void testRandomGiftsSingle_m10_0000_n5() {
        int money = 100000;
        int n = 5;
        int times = 100_10000;
        randomGifts(times, money, n);
    }

    @Test
    public void testRandomGifts_m10000_n10() throws InterruptedException {
        concurrentRandomGifts(8, 100000, 10000, 10);
    }

    @Test
    public void testRandomGifts_m1000_n100() throws InterruptedException {
        concurrentRandomGifts(8, 100000, 1000, 10);
    }

    // times:800000,total cost times:3747
    @Test
    public void testRandomGifts_m100_n10() throws InterruptedException {
        concurrentRandomGifts(8, 100000, 100, 10);
    }

    // times:800000,total cost times:96
    @Test
    public void testRandomGifts_10_10() throws InterruptedException {
        concurrentRandomGifts(8, 100000, 10, 10);
    }

    // times:800000,total cost times:114
    @Test
    public void testRandomGifts_m100000_n5() throws InterruptedException {
        concurrentRandomGifts(8, 100000, 100000, 5);
    }


    public void randomGifts(int times, int money, int n) {
        List<Integer> gifts;
        Integer sum;
        for (int i = 0; i < times; i++) {
            gifts = RandomGiftUtils.randomGifts(money, n);
            sum = RandomGiftUtils.sumGifts(gifts);
            Assert.isTrue(sum == money);
        }
    }

    public void concurrentRandomGifts(int th, int times, int money, int n) throws InterruptedException {
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
                        gifts = RandomGiftUtils.randomGifts(money, n);
                        System.out.println("curThName:" + thName + ",curTimes:" + j + ",gifts:" + gifts);
                        sum = RandomGiftUtils.sumGifts(gifts);
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
