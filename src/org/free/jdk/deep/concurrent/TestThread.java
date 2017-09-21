package org.free.jdk.deep.concurrent;

/**
 *
 * @author dongdaiming
 */
public class TestThread {

	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		 StackTraceElement[] a = Thread.currentThread().getStackTrace();
		System.out.println(a.length);
		for (StackTraceElement s : a) {
			System.out.println(s);
		}
	}
}
