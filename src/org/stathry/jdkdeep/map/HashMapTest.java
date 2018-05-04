/**
 * Copyright 2012-2016 free Co., Ltd.
 */
package org.stathry.jdkdeep.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author dongdaiming@free.com 2016年9月29日
 */
public class HashMapTest {

	@Test
    public void testInitArray() {
        Object[] a = new Object[16];
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
            Assert.assertEquals(null, a[i]);
        }

        int[] a2 = new int[16];
        for (int i = 0; i < a2.length; i++) {
            System.out.println(a2[i]);
            Assert.assertEquals(0, a2[i]);
        }
    }
	
	@Test
	public void testChar() {
		Map<Character, Integer> map = new HashMap<>();
		map.put('a', 1);
	}

	@Test
	public void testPutReturn() {
		Map<Character, Integer> map = new HashMap<>();
        Integer r1 = map.put('a', 1);
        System.out.println(r1);
        Assert.assertEquals(null, r1);

        Integer r2 = map.put('a', 2);
        System.out.println(r2);
        Assert.assertEquals(1, r2.intValue());

        Integer r3 = map.put('a', 3);
        System.out.println(r3);
        Assert.assertEquals(2, r3.intValue());
	}

	@Test
    public void testHash1() {
	   int h = 0;
	    Integer k = 100;
        h ^= k.hashCode();
        System.out.println(h);
    }

    //553415732
    @Test
    public void testHash78() {
        for (int i = 0; i < 20; i++) {
	   Object k = Integer.valueOf(i);
        System.out.println("k.hashS:" + k.hashCode());
        int eHash7 = hash7(k);
        int eHash8 = hash8(k);
        System.out.println("e.hash7:" + eHash7);
        System.out.println("e.hash8:" + eHash8);
        System.out.println("eHash8/eHash7:" + (eHash8 * 1.0/eHash7));
        System.out.println("eHash8/kHash:" + (eHash8 * 1.0/k.hashCode()));
        }
    }

    private int hash7(Object key) {
        int h = 0;
        h ^= key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        h = h ^ (h >>> 7) ^ (h >>> 4);
        return h;
    }

    private int hash8(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Test
    public void testMapCap() {
        int capacity1 = mapCap(1);
        System.out.println(capacity1);
        Assert.assertEquals(1, capacity1);

        int capacity2 = mapCap(2);
        System.out.println(capacity2);
        Assert.assertEquals(2, capacity2);

        int capacity3 = mapCap(3);
        System.out.println(capacity3);
        Assert.assertEquals(4, capacity3);

        int capacity4 = mapCap(4);
        System.out.println(capacity4);
        Assert.assertEquals(4, capacity4);

        int capacity5 = mapCap(5);
        System.out.println(capacity5);
        Assert.assertEquals(8, capacity5);
    }

    private int mapCap(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity)
            capacity <<= 1;
        return capacity;
    }

    @Test
    public void testEqualsHashcode() {
        HashSet<Name> set = new HashSet<Name>();
        set.add(new Name("abc" , "123"));
        set.add(new Name("abc" , "456"));
        System.out.println(set);
        Assert.assertEquals(1, set.size());
    }

    private class Name
    {
        private String first;
        private String last;
        public Name(String first, String last)
        {
            this.first = first;
            this.last = last;
        }
        // 根据 first 判断两个 Name 是否相等
        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }
            if (o.getClass() == Name.class)
            {
                Name n = (Name)o;
                return n.first.equals(first);
            }
            return false;
        }

        // 根据 first 计算 Name 对象的 hashCode() 返回值
        public int hashCode()
        {
            return first.hashCode();
        }

        public String toString()
        {
            return "Name[first=" + first + ", last=" + last + "]";
        }
    }

    @Test
    public void testLinkedHashMap1() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        System.out.println(set);
        Map.Entry<String, Integer>[] a = new Map.Entry[map.size()];
        set.toArray(a);
        System.out.println(a[0]);
        Assert.assertEquals(2, a.length);
        Assert.assertEquals(1, a[0].getValue().intValue());
        Assert.assertEquals(2, a[1].getValue().intValue());
    }

    @Test
    public void testLinkedHashMap2() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(8, 0.75f, true);
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.get("b");
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        for(Map.Entry<String, Integer> e: set) {
            System.out.println("k:" + e.getKey() + ",v:" + e.getValue());
        }
        System.out.println(set);
        Map.Entry<String, Integer>[] a = new Map.Entry[map.size()];
        set.toArray(a);
        Assert.assertEquals(3, a.length);
        Assert.assertEquals(1, a[0].getValue().intValue());
        Assert.assertEquals(3, a[1].getValue().intValue());
        Assert.assertEquals(2, a[2].getValue().intValue());
    }
}
