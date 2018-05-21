package org.stathry.jdkdeep.lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class EnumTest {

    @Test
    public void testEnumUsages() {
        Assert.assertEquals(3, Opt.ADD.apply(1 , 2));
        Assert.assertEquals(1, Opt.SUB.apply(3 , 2));
        Assert.assertEquals(6, Opt.MUL.apply(3 , 2));
        Assert.assertEquals(1, Opt.DIV.apply(3 , 2));

        Assert.assertEquals(Opt.ADD, Enum.valueOf(Opt.class, "ADD"));

        Assert.assertEquals(Opt.ADD, Opt.valueOf("ADD"));

        // values方法是在编译期间jvm创建的
        // 与values方法相似的有java.lang.Class.getEnumConstants()
        Assert.assertEquals(4, Opt.values().length);

        Set<Opt> opts1 = new HashSet<>(Arrays.asList(Opt.ADD, Opt.SUB));
        EnumSet<Opt> opts2 = EnumSet.of(Opt.ADD, Opt.SUB);
        Assert.assertEquals(opts1, opts2);
        opts2.add(Opt.DIV);
        Assert.assertEquals(3, opts2.size());
//        Set<Opt> opts3 = Collections.unmodifiableSet(opts2);
//        opts3.add(Opt.MUL);
    }

    @Test
    public void testEnumMapVsHashMap() {
        int times = 1000_10000;
        Opt[] es = Opt.values();
        EnumMap<Opt, String> eMap = new EnumMap(Opt.class);
        HashMap<Opt, String> hMap = new HashMap<>();
        for (int i = 0; i < es.length; i++) {
            eMap.put(es[i], "v" + i);
            hMap.put(es[i], "v" + i);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            for (Opt e : es) {
                eMap.get(e);
            }
        }
        long t1 = System.currentTimeMillis() - start;
        System.out.println("times " + times + ", EnumMap cost ms " + t1);

        start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            for (Opt e : es) {
                hMap.get(e);
            }
        }
        long t2 = System.currentTimeMillis() - start;
        System.out.println("times " + times + ", HashMap cost ms " + t2);
        System.out.println("times " + times + ", t2 : t1 = " + (t2 * 1.0 / t1));
    }
}
