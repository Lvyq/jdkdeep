package org.stathry.jdkdeep.collection;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

/**
 * TODO
 * 
 * @date 2018年2月7日
 */
public class FailFastTest {

	@Test()
	public void testListRemove1() {
		List<Integer> list = new ArrayList<>();
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(16);
		for (Integer i : list) {
			if (i % 2 == 0) {
				list.remove(i);
				System.out.println("removed:" + i);
			}
		}
		System.out.println(list);
	}

	// 由于remove引起list.size变小，导致未全部删除list中的偶数
	@Test
	public void testListRemove2() {
		List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
		for (int n = 0; n < list.size(); n++) {
			if (n % 2 == 0) {
				list.remove((Integer) n);
			}
		}
		assertEquals(3, list.size());
	}

	@Test
	public void testListRemove3() {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		Iterator<Integer> it = list.iterator();
		Integer i;
		for (; it.hasNext();) {
			i = it.next();
			if (i % 2 == 0) {
				it.remove();
				System.out.println("removed:" + i);
			}
		}
		System.out.println(list);
		assertEquals(3, list.size());
	}

	// 通过iterator并发地修改ArrayList(非同步容器)并不报错，只是结果并不是预期的
	@Test
	public void testListRemove4() throws InterruptedException {
		List<Integer> list = new ArrayList<>();
		int limit = 10000;
		int tn = 10;
		for (int i = 0; i < limit; i++) {
			list.add(i);
		}
		AtomicLong c = new AtomicLong();
		ExecutorService exec = Executors.newFixedThreadPool(tn);
		for (int j = 0; j < tn; j++) {
			exec.submit(() -> {
				Iterator<Integer> it = list.iterator();
				Integer i;
				for (; it.hasNext();) {
					i = it.next();
					if (i % 2 == 0) {
						it.remove();
						System.out.println(Thread.currentThread().getName() + ",removed:" + i);
						c.incrementAndGet();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}

		exec.awaitTermination(1, TimeUnit.MINUTES);
		System.out.println("count:" + c.get());
		System.out.println("list.size:" + list.size());
		assertEquals(limit / 2, list.size());
	}

	@Test(expected = ConcurrentModificationException.class)
	public void testMapRemove1() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "a");
		map.put(2, "b");
		map.put(3, "c");
		map.put(4, "d");
		map.put(5, "e");
		map.put(6, "f");
		for (Integer i : map.keySet()) {
			if (i % 2 == 0) {
				map.remove(i);
				System.out.println("removed:" + i);
			}
		}
		System.out.println(map);
	}

	@Test
	public void testMapRemove2() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "a");
		map.put(2, "b");
		map.put(3, "c");
		map.put(4, "d");
		map.put(5, "e");
		map.put(6, "f");
		Iterator<Integer> it = map.keySet().iterator();
		Integer i;
		for (; it.hasNext();) {
			i = it.next();
			if (i % 2 == 0) {
				it.remove();
				System.out.println("removed:" + i);
			}
		}
		System.out.println(map);
		assertEquals(3, map.size());
	}

}
