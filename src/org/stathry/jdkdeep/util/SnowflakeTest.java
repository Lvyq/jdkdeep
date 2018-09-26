package org.stathry.jdkdeep.util;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * Created by dongdaiming on 2018-07-23 16:08
 */
public class SnowflakeTest {

    @Test
    public void testGenIdSingle() {
        System.out.println(IPUtils.getCurIpQuietly());
        long dataCenterId = IPUtils.getHostFlagQuietly(31);
        System.out.println(dataCenterId);
        Snowflake snowflake = new Snowflake(dataCenterId, 1);

        System.out.println(snowflake.nextId());
        System.out.println("idLength:" + String.valueOf(snowflake.nextId()).length());
        Assert.isTrue(String.valueOf(snowflake.nextId()).length() == 18);

        long begin = System.currentTimeMillis();
        Long id;
        int limit = 100_0000;
        Set<Long> set = new HashSet<>(limit * 2);
        for (int i = 0; i < limit; i++) {
            id = snowflake.nextId();
//            System.out.println(id);
            set.add(id);
        }
        System.out.println(set.size());
        Assert.assertTrue(set.size() == limit);
        System.out.println("limit:" + limit + ", time:" + (System.currentTimeMillis() - begin));
    }

    @Test
    public void testGenIdConcurrent() throws InterruptedException {
        // 建议通过redis或zk递增id传入
        long dataCenterId = IPUtils.getHostFlagQuietly(31);
        Snowflake snowflake = new Snowflake(dataCenterId, 1);
        int tn = 8;
        int limit = 100_0000;
        ExecutorService exec = Executors.newFixedThreadPool(tn);
        List<Long> list = new Vector<>(tn * limit);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < tn; i++) {
            exec.execute(() -> {
                Long id;
                for (int j = 0; j < limit; j++) {
                    id = snowflake.nextId();
                    list.add(id);
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(5, TimeUnit.MINUTES);
        Set<Long> set = new HashSet<>(list);
        System.out.println("size:" + set.size() + ", time:" + (System.currentTimeMillis() - begin));
        Assert.assertTrue(set.size() == limit * tn);
    }
}
