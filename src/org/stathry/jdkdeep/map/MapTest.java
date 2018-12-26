package org.stathry.jdkdeep.map;

import org.junit.Assert;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

public class MapTest {

    private static final int mapSize = 1000_0000;

    @Test
    public void testHashMapMemory() {
        System.out.println("before:");
        printCurMemory();

        Map<Integer, Integer> map = new HashMap<>(mapSize * 2);
        for (int i = 0; i < mapSize; i++) {
            map.put(i, i);
        }

        System.out.println("after:");
        printCurMemory();
    }

    @Test
    public void testConcurrentHashMapMemory() {
        System.out.println("before:");
        printCurMemory();

        Map<Integer, Integer> map = new ConcurrentHashMap<>(mapSize * 2);
        for (int i = 0; i < mapSize; i++) {
            map.put(i, i);
        }

        System.out.println("after:");
        printCurMemory();
    }

    private void printCurMemory() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage(); //椎内存使用情况
        long totalMemorySize = memoryUsage.getInit(); //初始的总内存
        long maxMemorySize = memoryUsage.getMax(); //最大可用内存
        long usedMemorySize = memoryUsage.getUsed(); //已使用的内存
        System.out.println("TotalMemory:" + totalMemorySize/(1024*1024)+"M");
        System.out.println("FreeMemory:" + (totalMemorySize-usedMemorySize)/(1024*1024)+"M");
        System.out.println("MaxMemory:" + maxMemorySize/(1024*1024)+"M");
        System.out.println("UsedMemory:" + usedMemorySize/(1024*1024)+"M");
    }

    // 一般HashMap性能比TreeMap高，但Hash冲突严重时可能TreeMap更合适
    //    limit:1000000
    //    HashMap-Put:239
    //    TreeMap-Put:574
    //    HashMap-get:47
    //    TreeMap-get:44
    @Test
    public void testHashMapVSTreeMap() {
        int limit = 100_0000;
        System.out.println("limit:" + limit);
        long begin = System.currentTimeMillis();
        Map<Integer, Integer> map = new HashMap<>(limit * 2);
        for (int i = 0; i < limit; i++) {
            map.put(i, i);
        }
        System.out.println("HashMap-Put:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        Integer k, v;
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            k = e.getKey();
            v = e.getValue();
        }
        System.out.println("HashMap-get:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        Map<Integer, Integer> map2 = new TreeMap<>();
        for (int i = 0; i < limit; i++) {
            map2.put(i, i);
        }
        System.out.println("TreeMap-Put:" + (System.currentTimeMillis() - begin));

        begin = System.currentTimeMillis();
        for (Map.Entry<Integer, Integer> e : map2.entrySet()) {
            k = e.getKey();
            v = e.getValue();
        }
        System.out.println("TreeMap-get:" + (System.currentTimeMillis() - begin));
    }

    @Test
    public void testTreeMapSub() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 11);
        map.put(2, 22);
        map.put(3, 33);
        map.put(4, 44);
        System.out.println(map);
        System.out.println("firstEntry:" + map.firstEntry());
        System.out.println("lastEntry:" + map.lastEntry());
        System.out.println("firstKey:" + map.firstKey());
        System.out.println("lastKey:" + map.lastKey());

        SortedMap<Integer, Integer> sub1 = map.subMap(2, 4);
        System.out.println("subMap:" + sub1);
        assertEquals(2, sub1.size());

        SortedMap<Integer, Integer> tmap = map.tailMap(2);
        System.out.println("tailMap:" + tmap);
        assertEquals(3, tmap.size());

        SortedMap<Integer, Integer> hmap = map.headMap(2);
        assertEquals(1, hmap.size());
        System.out.println("headMap:" + hmap);
    }

}
