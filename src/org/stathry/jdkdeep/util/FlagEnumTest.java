package org.stathry.jdkdeep.util;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FlagEnumTest {

    private static FlagEnum[] a;

    @BeforeClass
    public static void init() {
        a = FlagEnum.values();
    }


    public static void main(String[] args) {
//        for (int i = 0; i < 10000; i++) {
//            System.out.println("E" + i + "(\"K"+ i +"\", \"V"+i+"\"),");
//        }
        System.out.println(2708*1.0/117);
    }

    // 117
    @Test
    public void testFast() throws InterruptedException {
        long start = System.currentTimeMillis();
        int n = 8;
        int limit = 100000;
//        FlagEnum[] a = FlagEnum.values();
        int len = a.length;
        ExecutorService exec = Executors.newFixedThreadPool(n);
        for(int i = 0; i < n; i++) {
            exec.submit(() -> {
                String s = "";
                for (int j = 0; j < limit; j++) {
                    s = FlagEnum.fastToCode(a[j % len].desc());
//                    System.out.println(s);
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(3, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    // 2708
    @Test
    public void testNormal() throws InterruptedException {
        {
            long start = System.currentTimeMillis();
            int n = 8;
            int limit = 100000;
//            FlagEnum[] a = FlagEnum.values();
            int len = a.length;
            ExecutorService exec = Executors.newFixedThreadPool(n);
            for(int i = 0; i < n; i++) {
                exec.submit(() -> {
                    String s = "";
                    for (int j = 0; j < limit; j++) {
                        s = FlagEnum.toCode(a[j % len].desc());
//                        System.out.println(s);
                    }
                });
            }
            exec.shutdown();
            exec.awaitTermination(3, TimeUnit.MINUTES);
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }
}
