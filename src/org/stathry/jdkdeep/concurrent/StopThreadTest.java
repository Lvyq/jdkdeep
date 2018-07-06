package org.stathry.jdkdeep.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 18/05/24
 */
public class StopThreadTest {

    @Test
    public void testOccurEXRestartThread() throws InterruptedException {
        Thread t = new Thread(new Task());
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("t " + t + " caught exception " + e);
                Thread nt = new Thread(new Task());
                nt.setUncaughtExceptionHandler(this);
                nt.start();
            }

        });
        t.start();
        Thread.sleep(60000);
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            for (int i = -5; i < 5; i++) {
                System.out.println(1 / i);
            }
        }
    }

    private static boolean stop = false;
    @Test
    public void testStopThread() throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stop) {
                    i++;
                    System.out.println(i);
                }
                System.out.println(Thread.currentThread().getName() + " , " + i);
            }
        }).start();
        TimeUnit.SECONDS.sleep(3);
        stop = true;
    }
}
