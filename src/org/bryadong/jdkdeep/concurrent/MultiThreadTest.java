package org.bryadong.jdkdeep.concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import org.junit.Test;

/**
 * TODO
 * 
 * @date 2018年1月31日
 */
public class MultiThreadTest {

	@Test
	public void testPrintHtreadInfos() {
		ThreadMXBean mxb = ManagementFactory.getThreadMXBean();
		ThreadInfo[] infos = mxb.dumpAllThreads(false, false);
		for (ThreadInfo ti : infos) {
			System.out.println(ti.getThreadId() + "\t" + ti.getThreadName());
		}
	}
	
}
