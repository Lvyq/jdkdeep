package org.stathry.jdkdeep.util;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import org.stathry.jdkdeep.util.AssertUtils;
import org.stathry.jdkdeep.util.OrderUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** 
* DistOrderUtils Tester. 
* 
* @author <Authors name> 
* @version 1.0
*/ 
public class OrderUtilsTest {

/**
* 
* Method: order() 
* 
*/ 
@Test
public void testOrder() throws Exception {
    OrderUtils orderUtils = new OrderUtils(24, OrderUtils.OrderFormat.TIME_SEQ, "yyyyMMddHHmmssSSS", 7);
//    System.out.println(orderUtils.order());
    int n = 8;
    int limit = 1000000;
    Integer Z = 0;
    Map<String,Integer> all = new ConcurrentHashMap<>();
    ExecutorService exec = Executors.newFixedThreadPool(n);
    long start = System.currentTimeMillis();
    for (int i = 0; i < n; i++) {
        exec.submit(() -> {
            String o = "";
            for(int j = 0; j < limit; j++) {
                o = orderUtils.order();
//                System.out.println(o);
                all.put(o, Z);
            }
        });
    }
    exec.shutdown();
    exec.awaitTermination(1, TimeUnit.SECONDS);
    System.out.println(all.size());
//    AssertUtils.isTrue(all.size() == (n * limit), "error.");
    System.out.println("n=" + n + ",limit=" + limit + ",map size=" + all.size() + ",ms=" + (System.currentTimeMillis() - start));
}

/** 
* 
* Method: order(String userId) 
* 
*/ 
@Test
public void testOrderUserId() throws Exception {
    String uid = "311045";
    OrderUtils orderUtils = new OrderUtils(30, OrderUtils.OrderFormat.TIME_SEQ_USER, "yyyyMMddHHmmssSSS", 7, 6);
//    System.out.println(orderUtils.order(uid));
    int n = 8;
    int limit = 100000;
    Integer Z = 0;
    Map<String,Integer> all = new ConcurrentHashMap<>();
    ExecutorService exec = Executors.newFixedThreadPool(n);
    long start = System.currentTimeMillis();
    for (int i = 0; i < n; i++) {
        exec.submit(() -> {
            String o = "";
            for(int j = 0; j < limit; j++) {
                o = orderUtils.order(uid);
                System.out.println(o);
                all.put(o, Z);
            }
        });
    }
    exec.shutdown();
    exec.awaitTermination(3, TimeUnit.MINUTES);
    System.out.println(all.size());
    AssertUtils.isTrue(all.size() == (n * limit), "error.");
    System.out.println("n=" + n + ",limit=" + limit + ",map size=" + all.size() + ",ms=" + (System.currentTimeMillis() - start));
}

} 
