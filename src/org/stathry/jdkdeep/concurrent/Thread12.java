package org.stathry.jdkdeep.concurrent;

/**
 * TODO
 */
public class Thread12 {

	static class Thread1 extends Thread {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "---run1");
		}
	}

	static class Thread2 extends Thread {

		private Thread preThread;

		public Thread2(Thread preThread) {
			this.preThread = preThread;
		}

		@Override
		public void run() {
			try {
				preThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "---run2");
		}
	}

}
