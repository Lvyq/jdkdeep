/**
 * Copyright 2012-2016 free Co., Ltd.
 */
package org.stathry.jdkdeep.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author dongdaiming@free.com 2016年9月29日
 */
public class HashMapTest {

    // n=数组长度
    // p = tab[i = (n - 1) & hash]

    // HashMap在单线程的情况下，这样操作是允许的: 插入数据 -> 遍历 -> 插入数据 -> 遍历...
    @Test
    public void testPutAndIterator() throws InterruptedException {
        int limit = 10000;
        final Map<Integer, Integer> map = new HashMap<>(limit * 2);
        for (int i = 0; i < limit; i++) {
            map.put(i, i << 1);
            if(i % 500 == 0) {
                for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                    System.out.println(e.getKey() + ", " + e.getValue());
                }
                System.out.println();
                System.out.println();
                Thread.sleep(3000);
            }
        }
    }
    // hashMap在填充数据时另一线程遍历会抛异常
    @Test
    public void testPuttingAndIterator() throws InterruptedException {
        int limit = 100_0000;
        ExecutorService exec = Executors.newFixedThreadPool(2);
        final Map<Integer, Integer> map = new HashMap<>(limit * 2);
        exec.execute(() -> {
            for (int i = 0; i < limit; i++) {
                map.put(i, i << 1);
            }
        });

        Thread.sleep(100);
        exec.execute(() -> {
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                System.out.println(e.getKey() + ", " + e.getValue());
            }
        });
        exec.shutdown();
        exec.awaitTermination(2, TimeUnit.MINUTES);
    }

    // HashMap的无序是指遍历出来的顺序和插入的顺序不一致。
    // 如果key的集合相同，则遍历出来的顺序一致
    @Test
    public void testMapOrder() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("d", 3);
        map.put("c", 4);
        System.out.println(map);
        StringBuilder b = mapKeys(map);
        Assert.assertEquals(b.toString(), "abcd");

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("b", 1);
        map2.put("a", 2);
        map2.put("d", 3);
        map2.put("c", 4);
        System.out.println(map2);
        StringBuilder b2 = mapKeys(map2);
        Assert.assertEquals(b2.toString(), "abcd");
    }

    private StringBuilder mapKeys(Map<String, Integer> map) {
        StringBuilder b = new StringBuilder();
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            b.append(e.getKey());
        }
        return b;
    }

    @Test
    public void testMapThreshold() {
        for (int i = 0; i < 20; i++) {
            System.out.println("cap=" + i + ", threshold=" + tableSizeFor(1));
        }
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
    }

    // 多次调用HashMap的entrySet方法会返回同一实例
    // 多次调用entrySet方法返回的实例的iterator方法返回的实例都是不同的
    @Test
    public void testMapIterator() {
        Map<Integer, Integer> map = new HashMap<>(32);
        Set<Map.Entry<Integer, Integer>> es1 = map.entrySet();
        Set<Map.Entry<Integer, Integer>> es2 = map.entrySet();
        Assert.assertEquals(es1, es2);
        Assert.assertNotEquals(es1.iterator(), es2.iterator());
        map.put(1, 1);
        Assert.assertEquals(es1, es2);
        Assert.assertNotEquals(es1.iterator(), es2.iterator());
        Set<Map.Entry<Integer, Integer>> es3 = map.entrySet();
        Assert.assertEquals(es3, es2);
        Assert.assertNotEquals(es3.iterator(), es2.iterator());

        Assert.assertNotEquals(es1.iterator(), es1.iterator());
    }

/*    @Test
    public void testMapHashCode() {
        Map<String, Integer> map1 = Map.of("a", 1, "b", 666888);
        Map<String, Integer> map11 = Map.of("a", 11, "b", 666888);
        Map<String, Integer> map2 = Map.of("b", 666888, "a", 1);
        System.out.println(map1.hashCode());
        System.out.println(map2.hashCode());
        Assert.assertEquals(map1.hashCode(), map2.hashCode());
        Assert.assertNotEquals(map1.hashCode(), map11.hashCode());
    }*/

    // 不断往map中put数据,put 10000个时再清空，会发现可用内存是先逐渐减小，然后再逐渐增大(这说明put到map中又被清除的数据被回收了)
//296796688
//287191744
//248771952
//95092784
//507089712
//488760616
//461266976
//452102424
    @Test
    public void testLoopPutAndClear() {
        long start = System.currentTimeMillis();
        Map<Long, Long> map = new HashMap<>();
        for (long i = 0, max = Long.MAX_VALUE; i < max; i++) {
            map.put(i, i);
            if (i % 10000 == 0) {
                map.clear();
                System.out.println(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start));
                System.out.println("freeMemory:" + Runtime.getRuntime().freeMemory());
            }
        }
    }

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
