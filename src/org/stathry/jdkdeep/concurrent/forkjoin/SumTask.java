package org.stathry.jdkdeep.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年8月11日
 */
public class SumTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = -4184018544104259388L;
	
	private static final int THRESHOD = 2;

	private int start;
	private int end;

	public SumTask(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		if((end - start) > THRESHOD) {
			System.out.println("fork, start=" + start + ",end=" + end);
			int middle = (end + start) / 2;
			SumTask leftTask = new SumTask(start, middle);
			SumTask righttTask = new SumTask(middle + 1, end);
			leftTask.fork();
			righttTask.fork();
			
			Integer lr = leftTask.join();
			Integer rr = righttTask.join();
			sum = lr + rr;
			
		} else {
			System.out.println("compute, start=" + start + ",end=" + end);
			for(int i = start; i <= end; i++) {
				sum += i;
			}
		}
		return sum;
	}

}
