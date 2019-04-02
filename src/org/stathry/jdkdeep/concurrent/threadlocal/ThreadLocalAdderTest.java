package org.stathry.jdkdeep.concurrent.threadlocal;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * 线程隔离计数器测试
 *
 * @author dongdaiming(董代明)
 * @date 2019-03-05 18:52
 */
public class ThreadLocalAdderTest {

    private int coreThreads = 4;
    private int tasks = 8;
    private int limit = 100_0000;

    /**
     * 线程复用的情况下调了remove计数就不会累加
     * @throws InterruptedException
     */
    @Test
    public void testConcurrentThreadLocalAddRemove() throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(coreThreads);
        LongAdder count = new LongAdder();
        // 设置任务数大于核心线程数使得发生线程复用
        for (int i = 0; i < tasks; i++) {
            int ti = i;
            exec.execute(() -> {
                ThreadLocalAdder.initLongAdder();
                for (int j = 0; j < limit; j++) {
                    ThreadLocalAdder.getLocalAdder().add(1);
                }

                int curCount = ThreadLocalAdder.getLocalAdder().intValue();
                count.add(curCount);
//                log.info("task: {}, count: {}", ti, curCount);
                Assert.assertEquals(limit, curCount);
                // 线程复用的情况下调remove计数不会累加
                ThreadLocalAdder.remove();
            });
        }
        exec.shutdown();
        exec.awaitTermination(3, TimeUnit.MINUTES);
//        log.info("totalCount: {}.", count.intValue());
        Assert.assertEquals(count.intValue(),tasks * limit);
    }

    /**
     * 线程复用的情况下不调remove计数会累加
     * @throws InterruptedException
     */
    @Test
    public void testConcurrentThreadLocalAddNoRemove() throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(coreThreads);
        LongAdder count = new LongAdder();
        // 设置任务数大于核心线程数使得发生线程复用
        for (int i = 0; i < tasks; i++) {
            int ti = i;
            exec.execute(() -> {
                for (int j = 0; j < limit; j++) {
                    ThreadLocalAdder.getLocalAdder().add(1);
                }

                int curCount = ThreadLocalAdder.getLocalAdder().intValue();
                count.add(curCount);
//                log.info("task: {}, count: {}", ti, curCount);
            });
        }
        exec.shutdown();
        exec.awaitTermination(3, TimeUnit.MINUTES);
//        log.info("totalCount: {}.", count.intValue());
        Assert.assertTrue(count.intValue() > tasks * limit);
    }
}
