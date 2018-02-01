package org.bryadong.jdkdeep.concurrent;

import static org.junit.Assert.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年8月10日
 */
public class TestCallable {

	@Test
	public void test() {
		Callable<Integer> call = new Caller(1, 2);
		FutureTask<Integer> f = new FutureTask<Integer>(call);
		Thread t = new Thread(f);
		t.start();

		try {
			Thread.sleep(1000);
			System.out.println(f.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	static class Caller implements Callable<Integer> {
		
		private int a;
		private int b;

		public Caller(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public Integer call() throws Exception {
			return a + b;
		}

	}

}
