/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * @author stathry@126.com
 * @date 2017年6月6日
 */
public class ListTest {

    // 当数据量较大时，set的查询速度比list快很多
    @Test
    public void testQueryArrayListVSHashSet() {
        int limit = 1_00000;
        long begin = System.currentTimeMillis();
        List<String> list = new ArrayList<>(limit);
        for (int i = 0; i < limit; i++) {
            list.add(UUID.randomUUID().toString());
        }
        System.out.println("list init time:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        Set<String> set = new HashSet<>(limit * 2);
        for (int i = 0; i < limit; i++) {
            set.add(UUID.randomUUID().toString());
        }
        System.out.println("set init time:" + (System.currentTimeMillis() - begin));

        String q;
        begin = System.currentTimeMillis();
        for (int i = 0; i < limit; i++) {
            q = UUID.randomUUID().toString();
            set.contains(q);
        }
        System.out.println("set query time:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        for (int i = 0; i < limit; i++) {
            q = UUID.randomUUID().toString();
            for (int j = 0; j < limit; j++) {
                if(list.get(i).equals(q)) {
                    break;
                }
            }
        }

        System.out.println("list query time:" + (System.currentTimeMillis() - begin));
    }

    @Test(expected = ConcurrentModificationException.class)
//    @Test
    public void testListEachRemove2() {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");

        for (String temp : a) {
            System.out.println("temp:" + temp);
            if ("2".equals(temp)) { a.remove(temp);
            }
        }
        System.out.println(a);
    }

    @Test
    public void testListEachRemove1() {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");

        // 因为删除掉一个元素后游标已经到达尾端了，所以不再调用Itr.next()，所以此处没有异常
        for (String temp : a) {
            System.out.println("temp:" + temp);
            if ("1".equals(temp)) { a.remove(temp);
            }
        }
        System.out.println(a);
    }

    @Test
    public void testVectorAddNull() {
        List<String> l = new Vector<>();
        l.add(null);
        System.out.println(l.size());
    }

    // 删除list中不存在的元素
    @Test
    public void testRemovNotExists() {
        List<Integer> list1 = new ArrayList<>(10);
        list1.remove(new Object());
    }

    // 删除list的指定区间的元素可以使用subList(f, t).clear()
    @Test
    public void testRemoveRange() {
        List<Integer> list1 = new ArrayList<>(10);
        List<Integer> list2 = new ArrayList<>(10);
        for (int i = 1; i <= 10 ; i++) {
            list1.add(i);
            list2.add(i);
        }

        for (int i = 0, j = 3; i < 3 ; i++) {
            list1.remove(j);
        }

        list2.subList(3, 6).clear();

        System.out.println(list1);
        System.out.println(list2);
        Assert.assertEquals(list1, list2);
    }

//    @Test
//    public void testRemoveIntOrInteger() {
//        List<Integer> list1 = new ArrayList<>(List.of(2, 4, 6));
//        list1.remove(2);
//        System.out.println(list1);
//        assertEquals(2, list1.size());
//        assertEquals(2, list1.get(0).intValue());
//        assertEquals(4, list1.get(1).intValue());
//
//        List<Integer> list2 = new ArrayList<>(List.of(2, 4, 6));
//        list2.remove(Integer.valueOf(2));
//        System.out.println(list2);
//        assertEquals(2, list2.size());
//        assertEquals(4, list2.get(0).intValue());
//        assertEquals(6, list2.get(1).intValue());
//    }


//    limit:100000000
//    testTraverse fori:434
//    testTraverse forEach:271
//    testTraverse forIterator:247
    @Test
    public void testTraverse() {
        int limit = 10000_0000;
        List<Integer> list = new ArrayList<>(limit);
        for (int i = 0; i < limit; i++) {
            list.add(i);
        }

        Integer n = 0;
        System.out.println("limit:" + limit);

        long begin = System.currentTimeMillis();
        for (int i = 0; i < limit; i++) {
            n = list.get(i);
        }
        System.out.println("testTraverse fori:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        for (Integer i : list) {
            n = i;
        }
        System.out.println("testTraverse forEach:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        Iterator<Integer> it = list.iterator();
        for (; it.hasNext(); ) {
            n = it.next();
        }
        System.out.println("testTraverse forIterator:" + (System.currentTimeMillis() - begin));
    }

    @Test
    public void testArrInsert() {
        List<String> list = null;
        //new ArrayList<>(List.of("a", "b", "c"));
        System.out.println(list);
        list.add(1, "x");
        System.out.println(list);

        // 模拟a[1]处插入'i'
        char[] a = new char[]{'x', 'y', 'z'};
        char[] t = new char[a.length + 1];
        int i = 1;
        System.arraycopy(a, 0, t, 0, a.length);
        a = t;
        System.out.println(Arrays.toString(a));
        System.arraycopy(a, i, a, i + 1, a.length - i - 1);
        a[i] = 'i';
        System.out.println(String.valueOf(a));
        System.out.println(String.valueOf(a).equals("xiyz"));
    }

    @Test
    public void testAddNum() {
        List<Number> list = new ArrayList<>();
        list.add(1);
        list.addAll(Arrays.asList(6, 7, 8));
        Set<Number> set = new HashSet<>(Arrays.asList(3, 4, 3));
        list.addAll(set);
		System.out.println(list);
		assertEquals(6, list.size());
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
		assertEquals(3, list1.size());
    }

    @Test
    public void testRetainList() {
    	// 类似这种构建List的方式还有guava的 Lists.newArrayList(1, 2);
        List<Integer> list1 = new ArrayList(Arrays.asList(1, 2, 4, 6, 8));
        List<Integer> list2 = new ArrayList(Arrays.asList(2, 3, 6));

		list1.retainAll(list2);
		System.out.println(list1);
		// ? 3
		assertEquals(2, list1.size());
    }

    @Test
    public void testNewFinalSet() {
        Set<Integer> set = new HashSet(Arrays.asList(2, 4, 6));
    }

//    @Test
//    public void testOfFinalCollection() {
//        Map<Integer, String> map = Map.of(1, "a", 2, "b", 3, "c");
//        List<Integer> list = List.of(2, 3, 4);
//        Set<Integer> set = Set.of(3, 4, 5);
//    }

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
