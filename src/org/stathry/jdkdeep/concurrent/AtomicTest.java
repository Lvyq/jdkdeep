package org.stathry.jdkdeep.concurrent;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO
 */
public class AtomicTest {

    private static class SyncInc {
        private int i;

        public SyncInc(int i) {
            this.i = i;
        }

        public synchronized int inc() {
            return ++i;
        }
    }

    //    testAtomicIncTime,limit:1000000,th:8, count:8000001 , time:149,138,160
    //      testSyncIncTime,limit:1000000,th:8, count:8000001 , time:346,323,238

    //     testAtomicIncTime,limit:1000000,th:80, count:80000001 , time:2160,2046,2038
    //       testSyncIncTime,limit:1000000,th:80, count:80000001 , time:1041,1555,1087
    // 综上，当锁竞争不激烈时(并发线程数=8)sync的耗时约为atomic耗时的1.5倍，
    // 当锁竞争激烈时(并发线程数=80)atomic的耗时约为sync耗时的2倍
    @Test
    public void testAtomicIncTime() throws InterruptedException {
        int ths = 8;
        int limit = 100_0000;
        ExecutorService exec = Executors.newFixedThreadPool(ths);
        AtomicLong a = new AtomicLong();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < ths; i++) {
            exec.submit(() -> {
                for (int j = 0; j < limit; j++) {
                    a.incrementAndGet();
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(3, TimeUnit.MINUTES);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ",limit:" + limit
                + ",th:" + ths + ", count:" + a.incrementAndGet() + " , time:" + (System.currentTimeMillis() - begin));
    }

    @Test
    public void testSyncIncTime() throws InterruptedException {
        int ths = 8;
        int limit = 100_0000;
        ExecutorService exec = Executors.newFixedThreadPool(ths);
        SyncInc si = new SyncInc(0);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < ths; i++) {
            exec.submit(() -> {
                for (int j = 0; j < limit; j++) {
                    si.inc();
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(3, TimeUnit.MINUTES);
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName() + ",limit:" + limit
                + ",th:" + ths + ", count:" + si.inc() + " , time:" + (System.currentTimeMillis() - begin));
    }

    private static final int max4 = 9999;

    private static volatile BoundAtomicInteger no4 = new BoundAtomicInteger(0, max4);

    public static String getIncrementSequence4() {
        int value = no4.incrementWithBound();
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
        String maxs = list.stream().max(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        }).get();
        System.out.println(maxs);
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
