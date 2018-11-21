package org.stathry.jdkdeep.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * TODO
 * Created by dongdaiming on 2018-11-06 11:11
 */
public class TreeMapTest {

    private TreeMap<String, Integer> initTreeMap() {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("B", 2);
        map.put("A", 1);
        map.put("D", 4);
        map.put("C", 3);
        return map;
    }

    @Test
    public void testFirstLast() {
        TreeMap<String, Integer> map = initTreeMap();
        System.out.println(map.firstEntry());
        System.out.println(map.lastEntry());
    }

    @Test
    public void testTailMap() {
        TreeMap<String, Integer> map = initTreeMap();

        SortedMap<String, Integer> tail = map.tailMap("C");
        System.out.println(tail);
        Assert.assertEquals(2, tail.size());

        Assert.assertEquals(3, (int)tail.get("C"));
        Assert.assertEquals(4, (int)tail.get("D"));
    }

    @Test
    public void testTailMap2() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(2, "a");
        map.put(8, "b");
        map.put(10, "c");
        map.put(15, "d");

        SortedMap<Integer, String> tail = map.tailMap(3);
        System.out.println(tail);
        Assert.assertEquals(3, tail.size());


        SortedMap<Integer, String> tail2 = map.tailMap(8, false);
        System.out.println(tail2);
        Assert.assertEquals(2, tail2.size());

        SortedMap<Integer, String> tail3 = map.tailMap(8, true);
        System.out.println(tail3);
        Assert.assertEquals(3, tail3.size());
    }

    @Test
    public void testHeadMap() {
        TreeMap<String, Integer> map = initTreeMap();

        SortedMap<String, Integer> tail = map.headMap("B", true);
        System.out.println(tail);
        Assert.assertEquals(2, tail.size());

        Assert.assertEquals(2, (int)tail.get("B"));
        Assert.assertEquals(1, (int)tail.get("A"));
    }
}
