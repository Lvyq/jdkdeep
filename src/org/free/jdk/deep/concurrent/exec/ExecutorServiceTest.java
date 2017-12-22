package org.free.jdk.deep.concurrent.exec;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年12月22日
 */
public class ExecutorServiceTest {
	
	@Test
	public void testSubmitRun1() throws InterruptedException, ExecutionException {
		ExecutorService exec = new ThreadPoolExecutor(10, 1000, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), 
				Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		List<Future<?>> futures = new ArrayList<>(3); 
		for(int i = 0; i < 3; i++) {
			futures.add(exec.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "___" + new Date().toLocaleString());
				}
			}));
		}
		exec.shutdown();
		exec.awaitTermination(60, TimeUnit.SECONDS);
		for (Future<?> future : futures) {
			System.out.println(future + "___" + future.get());
		}
	}
	
	@Test
	public void testSubmitRun2() throws InterruptedException, ExecutionException {
		ExecutorService exec = new ThreadPoolExecutor(10, 1000, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), 
				Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		List<Future<?>> futures = new ArrayList<>(3); 
		for(int i = 0; i < 3; i++) {
			futures.add(exec.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "___" + new Date().toLocaleString());
				}
			}, i));
		}
		exec.shutdown();
		exec.awaitTermination(60, TimeUnit.SECONDS);
		for (Future<?> future : futures) {
			System.out.println(future + "___" + future.get());
		}
	}
	@Test
	public void testSubmitCall() throws InterruptedException, ExecutionException {
		ExecutorService exec = new ThreadPoolExecutor(10, 1000, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), 
				Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		List<Future<?>> futures = new ArrayList<>(5); 
		for(int i = 0; i < 5; i++) {
			final int n = i;
			futures.add(exec.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					return (int) Math.pow(n, 2);
				}
			}));
		}
		exec.shutdown();
		exec.awaitTermination(60, TimeUnit.SECONDS);
		for (Future<?> future : futures) {
			System.out.println(future + "___" + future.get());
		}
	}

}
