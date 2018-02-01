package org.bryadong.jdkdeep.concurrent.lock;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2018年1月2日
 */
public class IncStrTest {

	private int n1 = 0;
	private int n3 = 0;
	private int c3 = 'A';

	public synchronized String orderStr3() {
		StringBuilder s = new StringBuilder();
		if (n3 >= 100) {
			n3 = 0;
			c3++;
		}
		if (c3 > 'Z') {
			c3 = 'A';
		}
		s.append((char) c3);
		if (n3 < 10) {
			s.append('0');
			s.append(n3);
		} else {
			s.append(n3);
		}
		n3++;
		return s.toString();
	}

	public synchronized char orderStr1() {
		if (n1 > 'Z') {
			n1 = 0;
		} else if (n1 == 10) {
			n1 = 'A';
		} else {
		}
		char c = ' ';
		if (n1 < 10) {
			c = String.valueOf(n1).charAt(0);
		} else {
			c = (char) n1;
		}
		n1++;
		return c;
	}

	@Test
	public void testorderStr1() {
		final IncStrTest o = new IncStrTest();
		for (int i = 0; i < 100; i++) {
			System.out.println(o.orderStr1());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int t = 8;
		final int n = 100;
		ExecutorService exec = Executors.newFixedThreadPool(t);
		final IncStrTest o = new IncStrTest();
		final List<Character> list = new Vector<>();
		final Map<Character, Boolean> map = new ConcurrentHashMap<>();
		// final List<String> list = new Vector<>();
		// final Map<String,Boolean> map = new ConcurrentHashMap<>();
		for (int i = 0; i < t; i++) {
			exec.submit(new Runnable() {

				@Override
				public void run() {
					char s = ' ';
					// String s = "";
					for (int i = 0; i < n; i++) {
						s = o.orderStr1();
						list.add(s);
						map.put(s, Boolean.TRUE);
						// s = o.orderStr3();
						// map.put(s, Boolean.TRUE);
						// list.add(s);
						// System.out.println(o.orderStr());
					}
				}
			});
		}
		exec.shutdown();
		exec.awaitTermination(5, TimeUnit.MINUTES);
		System.out.println(list);
		System.out.println(map.size() + "size ==  t * n" + (map.size() == t * n));
	}

}
