package org.free.jdk.deep.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年8月11日
 */
public class TestForkJoin {

	@Test
	public void test() throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		Future<Integer> task = pool.submit(new SumTask(1, 100));
		System.out.println(task.get());
	}
	
	@Test
	public void test2() {
		int sum = 0;
		for(int i = 1; i <= 100; i++) {
			sum += i;
		}
		System.out.println(sum);
	}

}
