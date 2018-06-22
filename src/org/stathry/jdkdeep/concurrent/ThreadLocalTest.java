package org.stathry.jdkdeep.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * Created by dongdaiming on 2018-06-13 17:36
 */
public class ThreadLocalTest {

    private static class LocalManager {

        private static final ThreadLocal<Long> time = new ThreadLocal<Long>() {

            @Override
            protected Long initialValue() {
                return (long) (System.currentTimeMillis() / new Random().nextInt(1000));
            }
        };

        public long getTime() {
            return time.get();
        }

    }

    @Test
    public void testMultiThreadAccessThreadLocal() throws InterruptedException {
        Set<Long> set = new CopyOnWriteArraySet<>();
        final LocalManager local = new LocalManager();
        int ths = 8;
        ExecutorService exec = Executors.newFixedThreadPool(ths);
        for (int i = 0; i < ths; i++) {
            exec.execute(() -> {
                set.add(local.getTime());
            });
        }
        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("set:" + set + ", size:" + set.size());
        Assert.assertEquals(ths, set.size());
    }

}
