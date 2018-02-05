package org.stathry.jdkdeep.concurrent;

/**
 * TODO
 */
public class PrinterXY {

	private Object lock = new Object();
	private boolean flagX = true;

	public void printX() {
		for (int i = 0; i < 10; i++) {
			synchronized (lock) {
				try {
					if (!flagX) {
						lock.wait();
					}
					System.out.println("runX-" + i);
					flagX = false;
					Thread.sleep(1000);
					lock.notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void printY() {
		for (int i = 0; i < 10; i++) {
			synchronized (lock) {
				try {
					if (flagX) {
						lock.wait();
					}
					System.out.println("runY-" + i);
					flagX = true;
					Thread.sleep(1000);
					lock.notify();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
