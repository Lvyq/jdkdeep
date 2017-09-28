package org.free.jdk.deep.concurrent;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

/**
 * TODO
 */
public class OrderNoGeneratorTest {

    @Test
    public void testConcurrent() throws InterruptedException {
        final List<String> orders = new CopyOnWriteArrayList<>();
        final OrderNoGenerator orderNoGenerator = new OrderNoGenerator("yyyyMMddHHmmss", "02", 999999);
        final CountDownLatch latch = new CountDownLatch(10000 * 100);
        ExecutorService exe = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            exe.submit(new Runnable() {
                
                @Override
                public void run() {
                    for(int j = 0; j < 100; j++) {
                        String order = orderNoGenerator.generateOrderNo();
                        System.out.println(order);
                        orders.add(order);
                        latch.countDown();
                    }
                }
            });
        }
        
        latch.await();
        
        System.out.println("list.size:" + orders.size());
        System.out.println("set.size:" + new HashSet<String>(orders).size());
    }
    
    @Test
    public void testPerSecond() throws InterruptedException {
        OrderNoGenerator orderNoGenerator = new OrderNoGenerator("yyyyMMddHHmmss", "02", 999999);
        AtomicLong counter = new AtomicLong();
        long start = System.currentTimeMillis();
        while(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start) < 1) {
            orderNoGenerator.generateOrderNo();
            counter.incrementAndGet();
        }
        System.out.println(counter.get());
    }
}
