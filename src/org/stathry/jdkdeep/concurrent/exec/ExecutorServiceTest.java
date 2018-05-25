package org.stathry.jdkdeep.concurrent.exec;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年12月22日
 */
public class ExecutorServiceTest {
	
	@Test(expected = RejectedExecutionException.class)
	public void testRejectedExecFullQ() throws InterruptedException, ExecutionException {
	    int maxQueueSize = 2000;
		ExecutorService exec = new ThreadPoolExecutor(8, 100, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(maxQueueSize),
				new NamedThreadFactory("exec-testR1-"), new ThreadPoolExecutor.AbortPolicy());
		for(int i = 0; i < 4000; i++) {
		    int n = i;
			exec.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "___" + n + ", " + System.currentTimeMillis());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
			});
		}
		exec.shutdown();
		exec.awaitTermination(60, TimeUnit.SECONDS);
	}

	// newFixedThreadPool使用的队列大小为Integer.MAX_VALUE,容易造成请求堆积，内存溢出
	@Test
	public void testOOMFullQ() throws Exception {
		ExecutorService exec = Executors.newFixedThreadPool(1);
		long max = (long)Integer.MAX_VALUE * 2L;
		for(long i = 0; i < max; i++) {
		    long n = i;
			exec.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "___" + n + ", " + System.currentTimeMillis());
                }
			});
		}
		exec.shutdown();
		exec.awaitTermination(300, TimeUnit.SECONDS);
	}

	@Test
    public void testSubmitRun12() throws InterruptedException, ExecutionException {
        int maxQueueSize = 5000;
        ExecutorService exec = new ThreadPoolExecutor(10, 1000, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(maxQueueSize),
                new NamedThreadFactory("exec-testR121-"), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++) {
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "___" + System.currentTimeMillis());
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(60, TimeUnit.SECONDS);

        ExecutorService exec2 = new ThreadPoolExecutor(10, 1000, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(maxQueueSize),
                new NamedThreadFactory("exec-testR122-"), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++) {
            exec2.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "___" + System.currentTimeMillis());
                }
            });
        }
        exec2.shutdown();
        exec2.awaitTermination(60, TimeUnit.SECONDS);
    }
	
	@Test
	public void testSubmitRun2() throws InterruptedException, ExecutionException {
		ExecutorService exec = new ThreadPoolExecutor(10, 1000, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
				Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
		List<Future<?>> futures = new ArrayList<>(3); 
		for(int i = 0; i < 3; i++) {
			futures.add(exec.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "___" + System.currentTimeMillis());
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
		ExecutorService exec = new ThreadPoolExecutor(10, 1000, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
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

	@Test
	public void testCompService() throws InterruptedException, ExecutionException {
	    int tn = 4;
	    int times = 32;
		ExecutorService exec = Executors.newFixedThreadPool(tn);
        CompletionService<Long> ce = new ExecutorCompletionService(exec);
		for(int i = 1; i <= tn; i++) {
		    final int n = i;
			ce.submit(new Callable<Long>() {

				@Override
				public Long call() throws Exception {
				    long sum = 0;
                    for (int j = 0; j < times; j++) {
                        sum += (long) Math.pow(n, j);
                    }
                    System.out.println(Thread.currentThread().getName() + ", " + ", time " + System.currentTimeMillis() + ", cur " + n + ", sum " + sum);
					return sum;
				}
			});
		}
		exec.shutdown();
		long total = 0;
		long cur = 0;
        for (int i = 0; i < tn ; i++) {
            cur = ce.poll(3, TimeUnit.MINUTES).get();
            total += cur;
            System.out.println("cur:" + i + ", time " + System.currentTimeMillis() + ", sum " + cur);
        }
        System.out.println("total:" + total);
	}

	private static class NamedThreadFactory implements ThreadFactory {

        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NamedThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" + poolNumber.getAndIncrement() + "-thread-";
        }

        NamedThreadFactory(String namePrefix) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = namePrefix == null || namePrefix.trim().length() == 0
                    ? "pool-" + poolNumber.getAndIncrement() + "-thread-" : namePrefix ;
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }

    }

}
