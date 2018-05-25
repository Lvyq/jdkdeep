/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.collection;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author stathry@126.com
 * @date 2017年6月6日
 */
public class ArrayListTest {

    @Test
    public void testAddNum() {
        List<Number> list = new ArrayList<>();
        list.add(1);
        list.addAll(Arrays.asList(6, 7, 8));
        Set<Number> set = new HashSet<>(Arrays.asList(3, 4, 3));
        list.addAll(set);
		System.out.println(list);
		Assert.assertEquals(6, list.size());
    }

    @Test(expected = ArrayStoreException.class)
    public void testArrayStoreEx() {
        Object[] a = new Long[2];
        a[0] = 888L;
        a[1] = "s";
		System.out.println(Arrays.toString(a));
    }

    @Test
    public void testIncompatibleTypes() {
    	// incompatible types 类型不相容
//        List<Object> a = new ArrayList<Long>();
    }

    @Test()
    public void testGeneralList1() {
        List<?> list1 = new ArrayList<Integer>(Arrays.asList(1, 3, 5));
        // 不能往统配List中添加元素
//        list1.add(1);
		System.out.println(list1);
    }

    // Arrays.asList返回的是不可更新的List
    @Test(expected = UnsupportedOperationException.class)
    public void testAddFinalList1() {
        List<Integer> list1 = Arrays.asList(1, 2, 4, 6, 8);
		list1.add(1);
		System.out.println(list1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRetainFinalList1() {
        List<Integer> list1 = Arrays.asList(1, 2, 4, 6, 8);
        List<Integer> list2 = Arrays.asList(2, 3, 6);

		list1.retainAll(list2);
		System.out.println(list1);
		Assert.assertEquals(3, list1.size());
    }

    @Test
    public void testRetainList() {
    	// 类似这种构建List的方式还有guava的 Lists.newArrayList(1, 2);
        List<Integer> list1 = new ArrayList(Arrays.asList(1, 2, 4, 6, 8));
        List<Integer> list2 = new ArrayList(Arrays.asList(2, 3, 6));

		list1.retainAll(list2);
		System.out.println(list1);
		// ? 3
		Assert.assertEquals(2, list1.size());
    }

    @Test
    public void testNewFinalSet() {
        Set<Integer> set = new HashSet(Arrays.asList(2, 4, 6));
    }

    @Test
    public void testOfFinalCollection() {
        Map<Integer, String> map = Map.of(1, "a", 2, "b", 3, "c");
        List<Integer> list = List.of(2, 3, 4);
        Set<Integer> set = Set.of(3, 4, 5);
    }

    @Test
    public void testGenericList() {
        List<Object> list1 = new ArrayList<>();
        list1.add(new ArrayList<String>());
		System.out.println(list1);

		// 编译会有警告
        List list2 = new ArrayList<>();
		list2.add(new ArrayList<String>());
		System.out.println(list2);

    }

    @Test
    public void testCollectionsNCopies() {
        List<String> list = Collections.nCopies(5, "a");
        System.out.println(list);
    }

	@Test
	public void testAddByIndex() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add(1, "x");
		Assert.assertTrue(list.get(0).equals("a"));
		Assert.assertTrue(list.get(1).equals("x"));
		Assert.assertTrue(list.get(2).equals("b"));
		Assert.assertTrue(list.get(3).equals("c"));
	}

	static class Order {
		private Long id;
		private String orderNo;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

	}

	@Test
	public void testSpilt() {
		int limit = 1000;
		List<Order> orders = new ArrayList<>(limit);
		Order o;
		for (int i = 0; i < limit; i++) {
			o = new Order();
			o.setOrderNo("NO" + i);
			o.setId((long) i);
			orders.add(o);
		}
		List<Order> list1 = orders.parallelStream().filter(new Predicate<Order>() {

			@Override
			public boolean test(Order t) {
				return t.getId() < 100;
			}
		}).collect(Collectors.toList());
		List<Order> list2 = orders.parallelStream().filter(new Predicate<Order>() {

			@Override
			public boolean test(Order t) {
				return t.getId() < 200 && t.getId() >= 100;
			}
		}).collect(Collectors.toList());

		System.out.println(list1.size());
		System.out.println(list2.size());
	}

	@Test
	public void testCon() {
	}

}
