package org.stathry.jdkdeep.vm;

import org.junit.Test;
import org.stathry.jdkdeep.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * Created by dongdaiming on 2018-06-15 11:41
 */
public class TestJVM {

    // java -Xmx1024M -Xms64M org.stathry.jdkdeep.vm.TestJVM
    // D:\workspace3\jdkdeep\out\production\jdkdeep路径下运行
    @Test
    public void testPrintMemory() {
        System.out.println("hello,jvm!");
        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory()/1024/1024;
        long totalMemory = rt.totalMemory()/1024/1024;
        System.out.println("maxMemory:" + maxMemory + "M");
        System.out.println("totalMemory(minUsedMemory):" + totalMemory + "M");
        Assert.isTrue(maxMemory == 1024);
        Assert.isTrue(totalMemory == 64);

        String env = System.getenv("jdkdeep.env");
        System.out.println("jdkdeep.env:" + env);
        Assert.isTrue("test".equals(env));
    }

    @Test
    public void testLoopPrintMemory() throws InterruptedException {
        int tn = 8;
        int CAPACITY = 10000_0000;
        ExecutorService exec = Executors.newFixedThreadPool(tn);
        for (int i = 0; i < tn; i++) {
            exec.execute(() -> {
                List<Integer> list = new ArrayList<>(CAPACITY);
                for (int j = 0; j < CAPACITY ; j++) {
                    list.add(j);
                    if(j % 1000 == 0 && j > 0) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(5, TimeUnit.MINUTES);
    }

    @Test
    public void testOOM() {
        int CAPACITY = 10_0000_0000;
        List<Integer> list = new ArrayList<>(CAPACITY);
        for (int i = 0; i < 10000_0000; i++) {
            list.add(i);
        }
    }

}
