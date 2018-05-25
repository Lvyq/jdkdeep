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
