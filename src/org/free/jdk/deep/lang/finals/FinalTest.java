package org.free.jdk.deep.lang.finals;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * TODO
 * 
 * @date 2018年1月31日
 */
public class FinalTest {

	private ExecutorService exec1 = Executors.newFixedThreadPool(4);
	private ExecutorService exec2 = Executors.newFixedThreadPool(4);

	@Test
	public void test() {
		for (int i = 0; i < 1; i++) {
			exec1.submit(() -> {
				FinalExample.writer();
			});

		}
		for (int j = 0; j < 10; j++) {
			exec2.submit(() -> {
				FinalExample.reader();
			});
		}
	}
}
