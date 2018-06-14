package org.stathry.jdkdeep.concurrent;

import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年9月4日
 */
public class VolatileTest {

    private static class LoopExecutor implements Runnable {

//        private boolean stop = false;
        private volatile boolean stop = false;

        public void stopIt() {
            stop = true;
        }

        @Override
        public void run() {
            int i = 0;
            while (!stop) {
                i++;
//                System.out.println("i:" + i++ + ",time:" + System.currentTimeMillis());
            }
            System.out.println("stopped at " + System.currentTimeMillis());
        }
    }

    // 当stop声明为非volatile变量时，主线程对stop变量的修改未能反应到子线程中，因此子线程未打印stopped at time.
    // 当stop声明为volatile变量时，主线程对stop变量的修改会立即反应到子线程中，因此子线程会打印stopped at time.并且start与stop的时间差约为1s.
    @Test
    public void testStopLoopExec() throws InterruptedException {
        LoopExecutor exe = new LoopExecutor();
        new Thread(exe).start();
        System.out.println("start at " + System.currentTimeMillis());
        Thread.sleep(1000);
        exe.stopIt();
        Thread.sleep(3000);
    }

	@Test
	public void test1() throws InterruptedException {
		final VolatileInc test = new VolatileInc();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}

		Thread.sleep(3000);
		System.out.println(test.inc);
	}
	
	@Test
	public void test2() throws InterruptedException {
		final VolatileInc2 test = new VolatileInc2();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}
		
		Thread.sleep(3000);
		System.out.println(test.inc);
	}
	
	@Test
	public void test3() throws InterruptedException {
		final VolatileInc3 test = new VolatileInc3();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}
		
		Thread.sleep(3000);
		System.out.println(test.inc);
	}
	
	@Test
	public void test4() throws InterruptedException {
		final VolatileInc4 test = new VolatileInc4();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}
		
		Thread.sleep(3000);
		System.out.println(test.i.get());
	}
}