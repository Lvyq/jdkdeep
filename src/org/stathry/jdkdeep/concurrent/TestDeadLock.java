package org.stathry.jdkdeep.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 检测死锁
 *
 * @author dongdaiming
 * @date 2018/5/18
 */
public class TestDeadLock {

    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final int LIMIT = 10000;
    private static final int TASKS = 2000;
    private static final int TASK_TIMEOUT = 10;

    @Test
    public void testSyncDeadLock() throws InterruptedException {
        runSyncTask(true);
    }

    @Test
    public void testSyncNoDeadLock() throws InterruptedException {
        runSyncTask(false);
    }

    @Test
    public void testJUCDeadLock() throws InterruptedException {
        runJUCLockTask(true);
    }

    @Test
    public void testJUCNoDeadLock() throws InterruptedException {
        runJUCLockTask(false);
    }

    private void runSyncTask(boolean deadLock) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(TASKS);
        Object lock1 = new Object();
        Object lock2 = new Object();
        AtomicLong deadLockCounter1 = new AtomicLong();
        AtomicLong deadLockCounter2 = new AtomicLong();

        for (int i = 0; i < LIMIT; i++) {
            exec.execute(new SyncTask(lock1, lock2, i, deadLockCounter1));
            if (deadLock) {
                exec.execute(new SyncTask(lock2, lock1, i, deadLockCounter2));
            } else {
                exec.execute(new SyncTask(lock1, lock2, i, deadLockCounter2));
            }

        }

        exec.shutdown();

        exec.awaitTermination(TASK_TIMEOUT, TimeUnit.SECONDS);
        System.out.println("runSyncTask completed, deadLock:" + deadLock + ", limit:" + LIMIT
                + ", counter1:" + deadLockCounter1.get() + ", counter2:" + deadLockCounter2.get());

        assertDeadLockCount(deadLock, deadLockCounter1, deadLockCounter2);
    }

    private void runJUCLockTask(boolean deadLock) throws InterruptedException {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        ExecutorService exec = Executors.newFixedThreadPool(TASKS);
        AtomicLong deadLockCounter1 = new AtomicLong();
        AtomicLong deadLockCounter2 = new AtomicLong();

        for (int i = 0; i < LIMIT; i++) {
            exec.submit(new LockTask(lock1, lock2, i, deadLockCounter1));
            if (deadLock) {
                exec.submit(new LockTask(lock2, lock1, i, deadLockCounter2));
            } else {
                exec.submit(new LockTask(lock1, lock2, i, deadLockCounter2));
            }

        }

        exec.shutdown();

        exec.awaitTermination(TASK_TIMEOUT, TimeUnit.SECONDS);
        System.out.println("runJUCLockTask completed, deadLock:" + deadLock + ", limit:" + LIMIT
                + ", counter1:" + deadLockCounter1.get() + ", counter2:" + deadLockCounter2.get());

        assertDeadLockCount(deadLock, deadLockCounter1, deadLockCounter2);
    }

    private void assertDeadLockCount(boolean deadLock, AtomicLong counter1, AtomicLong counter2) {
        if(deadLock) {
            Assert.assertTrue(counter1.get() < LIMIT);
            Assert.assertTrue(counter2.get() < LIMIT);
        } else {
            Assert.assertEquals(LIMIT, counter1.get());
            Assert.assertEquals(LIMIT, counter2.get());
        }
    }

    private static class SyncTask implements Runnable {

        private Object lock1;
        private Object lock2;
        private int i;
        private AtomicLong counter;

        public SyncTask(Object lock1, Object lock2, int i, AtomicLong counter) {
            this.lock1 = lock1;
            this.lock2 = lock2;
            this.i = i;
            this.counter = counter;
        }

        @Override
        public void run() {
            SimpleDateFormat formatter = new SimpleDateFormat(TIME_PATTERN);
            String tName = Thread.currentThread().getName();
//            System.out.println(i + ", LockTask " + tName + ", start get Lock, time " + formatter.format(new Date()));

            synchronized (lock1) {
                System.out.println(i + ", SyncTask " + tName + ", get outLock " + lock1 + " success. time " + formatter.format(new Date()));
                synchronized (lock2) {
                    System.out.println(i + ", SyncTask " + tName + ", get inLock " + lock2 + "-" + counter.incrementAndGet()
                            + " success. time " + formatter.format(new Date()));
                }
            }
        }

    }

    private static class LockTask implements Runnable {

        private Lock lock1;
        private Lock lock2;
        private int i;
        private AtomicLong counter;

        public LockTask(Lock lock1, Lock lock2, int i, AtomicLong counter) {
            this.lock1 = lock1;
            this.lock2 = lock2;
            this.i = i;
            this.counter = counter;
        }

        @Override
        public void run() {
            SimpleDateFormat formatter = new SimpleDateFormat(TIME_PATTERN);
            String tName = Thread.currentThread().getName();
//            System.out.println(i + ", LockTask " + tName + ", start get Lock, time " + formatter.format(new Date()));

            try {
                lock1.lock();
                System.out.println(i + ", LockTask " + tName + ", get outLock " + lock1 + " success. time " + formatter.format(new Date()));
                lock2.lock();
                System.out.println(i + ", LockTask " + tName + ", get inLock " + lock2 + "-" + counter.incrementAndGet() + " success. time " + formatter.format(new Date()));
            } finally {
                lock2.unlock();
                lock1.unlock();
            }

        }
    }

}
