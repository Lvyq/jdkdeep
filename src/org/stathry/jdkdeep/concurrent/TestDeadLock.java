package org.stathry.jdkdeep.concurrent;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 检测
 *
 * @author dongdaiming
 * @date 2018/5/18
 */
public class TestDeadLock {

    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    @Test
    public void testDeadLock() throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            exec.submit(new Task1());
            exec.submit(new Task2());
        }
        System.out.println("task submit completed.");
        exec.shutdown();
        exec.awaitTermination(5, TimeUnit.MINUTES);
    }

    @Test
    public void testLoopRetryLock() throws InterruptedException {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            exec.submit(new Task3(lock1, lock2, i));
            exec.submit(new Task4(lock1, lock2, i));
        }
        System.out.println("task submit completed.");
        exec.shutdown();
        exec.awaitTermination(5, TimeUnit.MINUTES);
    }

    private static class Task1 implements Runnable {

        @Override
        public void run() {
            SimpleDateFormat formatter = new SimpleDateFormat(TIME_PATTERN);
            String tName = Thread.currentThread().getName();
            synchronized (TestDeadLockC1.class) {
                System.out.println("task1 " + tName + " get lock o1 success. time " + formatter.format(new Date()));
            }
            synchronized (TestDeadLockC2.class) {
                System.out.println("task1 " + tName + " get lock o1, o2 success. time " + formatter.format(new Date()));
            }
        }
    }

    private static class Task2 implements Runnable {

        @Override
        public void run() {
            SimpleDateFormat formatter = new SimpleDateFormat(TIME_PATTERN);
            String tName = Thread.currentThread().getName();
            synchronized (TestDeadLockC2.class) {
                System.out.println("task2 " + tName + " get lock o2 success. time " + formatter.format(new Date()));
            }
            synchronized (TestDeadLockC1.class) {
                System.out.println("task2 " + tName + " get lock o2, o1 success. time " + formatter.format(new Date()));
            }
        }
    }

    private static class Task3 implements Runnable {

        private Lock lock1;
        private Lock lock2;
        private int i;

        public Task3(Lock lock1, Lock lock2, int i) {
            this.lock1 = lock1;
            this.lock2 = lock2;
            this.i = i;
        }

        @Override
        public void run() {
            SimpleDateFormat formatter = new SimpleDateFormat(TIME_PATTERN);
            String tName = Thread.currentThread().getName();
            while (true) {
                try {
                    if (lock1.tryLock()) {
                        if (lock2.tryLock()) {
                            System.out.println(i + ", task3 " + tName + " try get lock o1, o2 success. time " + formatter.format(new Date()));
                            break;
                        } else {
                            System.out.println(i + ", task3 " + tName + " try get lock o1, o2 failed. time " + formatter.format(new Date()));
                        }
                    } else {
                        System.out.println(i + ", task3 " + tName + " try get lock o1, o2 failed. time " + formatter.format(new Date()));
                    }
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Task4 implements Runnable {

        private Lock lock1;
        private Lock lock2;
        private int i;

        public Task4(Lock lock1, Lock lock2, int i) {
            this.lock1 = lock1;
            this.lock2 = lock2;
            this.i = i;
        }

        @Override
        public void run() {
            SimpleDateFormat formatter = new SimpleDateFormat(TIME_PATTERN);
            String tName = Thread.currentThread().getName();
            while (true) {
                try {
                    if (lock2.tryLock()) {
                        if (lock1.tryLock()) {
                            System.out.println(i + ", task4 " + tName + " try get lock o2, o1 success. time " + formatter.format(new Date()));
                            break;
                        } else {
                            System.out.println(i + ", task4 " + tName + " try get lock o2, o1 failed. time " + formatter.format(new Date()));
                        }
                    } else {
                        System.out.println(i + ", task4 " + tName + " try get lock o2, o1 failed. time " + formatter.format(new Date()));
                    }
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class TestDeadLockC1 {
    }

    private static class TestDeadLockC2 {
    }

}
