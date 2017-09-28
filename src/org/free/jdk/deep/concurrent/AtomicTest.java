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
public class AtomicTest {

    private static final long max4 = 9999L;

    private static volatile AtomicLongBound no4 = new AtomicLongBound(0);

    public static String getIncrementSequence4() {
        long value = no4.incrementAndGet(max4);
        return String.format("%04d", value);
    }

    @Test
    public void test1() {
        System.out.println(getIncrementSequence4());
    }

    @Test
    public void test2() throws InterruptedException {
        final AtomicLong counter = new AtomicLong();
        final List<String> list = new CopyOnWriteArrayList<>();
        final CountDownLatch latch = new CountDownLatch(100 * 1000);
        ExecutorService exe = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            exe.submit(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        String s = getIncrementSequence4();
                        list.add(s);
                        if (s.length() > 4) {
                            System.out.println("error lenth." + s);
                        }
                        counter.incrementAndGet();
                        latch.countDown();
                    }
                }
            });
        }
        latch.await();
        System.out.println("list.size:" + list.size());
        System.out.println("set.size:" + new HashSet<String>(list).size());
        System.out.println("counter:" + counter.get());
        StringBuilder a = new StringBuilder();
        for(String s : list) {
            a.append(s);
            a.append(",");
        }
        System.out.println(a.toString());
    }
    
    @Test
    public void test3() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1000 * 1000);
        ExecutorService exe = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 1000; i++) {
            exe.submit(new Runnable() {
                
                @Override
                public void run() {
                    for(int j = 0; j < 1000; j++) {
                        System.out.println(System.currentTimeMillis());
                        latch.countDown();
                    }
                }
            });
        }
        latch.await();
    }
    
}
