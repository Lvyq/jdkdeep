/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author stathry@126.com
 * @date 2017年6月1日
 */
public class SyncTest {

    @Test
    public void testSyncSleep() throws InterruptedException {
        // t0和main两个线程谁先获取到同一对象的锁是不确定的.如果主线程获取到锁，由于主线程sleep完了之后主线程终结就等不到t0输出了
        // 如果主线程在调m2之前稍微sleep几毫米，则主线程会被阻塞直到t0执行完成释放锁
        SyncSleepTask task = new SyncSleepTask();
        new Thread(task).start();
//        Thread.sleep(100);
        task.m2();
        Assert.assertEquals(2000, task.b);
        task.printDateThStr("main b=" + task.b);
    }

    @Test
    public void testSyncSleepMulti() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            SyncSleepTask task = new SyncSleepTask();
            new Thread(task).start();
            task.m2();
            Assert.assertEquals(2000, task.b);
            System.out.println("main b=" + task.b);
            System.out.println();
            System.out.println();
        }
    }

	@Test(expected = NullPointerException.class)
	public void test1() {
		try {
			sync1(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class SyncSleepTask implements Runnable {
	    int b = 100;

        public synchronized void m1() throws InterruptedException {
            printDateThStr("m1");
            b = 1000;
            Thread.sleep(5000);
            printDateThStr("m1 b=" + b);
        }

	    public synchronized void m2() throws InterruptedException {
            printDateThStr("m2");
            Thread.sleep(3000);
            b = 2000;
        }

        public void printDateThStr(String str) {
            StringBuilder sb = new StringBuilder(Thread.currentThread().getName()).append(" -- ");
            sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())).append(" -- ");
            System.out.println(sb.append(str).toString());
        }

        @Override
        public void run() {
            try {
                m1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void sync1(Object o) {
		synchronized (o) {
			System.out.println(o);
		}
	}

	static class SyncImpl extends AbstractSync {

        @Override
        protected void m() {

        }

        @Override
        protected void m1() {

        }
    }

}
