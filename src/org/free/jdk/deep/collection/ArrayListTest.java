/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月6日
 */
public class ArrayListTest {
    
	@Test
	public void testForeachRemove1() {
	      List<String> list = new ArrayList<>();
	        list.add("1");
	        list.add("2");
	        list.add("3");
	        list.add("4");
	        list.add("5");
	        for (String item : list) {
	            if (item.equals("4")) {
	                System.out.println(item);
	                list.remove(item);
	            }
	        }
	        System.out.println(list.size());
	}
	
	@Test
	public void testForeachRemove2() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		for (String item : list) {
			list.remove(item);
			System.out.println("romoved:" + item);
		}
		System.out.println(list.size());
	}
	@Test
	public void testForeachRemove3() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println();
		for (Integer item : list) {
			list.remove(item);
			System.out.println("romoved:" + item);
		}
	}
	
	@Test
	public void testForeachRemove4() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(list);
		for (int i = 0; i < list.size(); i++) {
			list.remove(i);
		}
		System.out.println(list);
	}
	@Test
	public void testForeachRemove5() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(list);
		Iterator<Integer> i = list.iterator();
		while(i.hasNext()) {
			i.next();
			i.remove();
		}
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
	        o.setId((long)i);
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
	
}
