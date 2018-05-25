package org.stathry.jdkdeep.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author dongdaiming
 * @date 18/05/25
 */
public class FieldAccessTimeTest {

    private long time = 2018;
    private static final int LIMIT = 1000_0000;
    private static final int TEST_TIMES = 100;

    // testReadLocalVar,test times 100, every calc limit 10000000, avg time 11.500000
    @Test
    public void testReadLocalVar() {
        List<Long> list = new ArrayList<>(TEST_TIMES);
        List<Long> datas = new ArrayList<>(TEST_TIMES);
        for (int j = 0; j < TEST_TIMES; j++) {
            long start = System.currentTimeMillis();
            long sum = 0;
            long t = time;
            for (int i = 0; i < LIMIT; i++) {
                sum = sum + t + i;
            }
            datas.add(sum);
            list.add((System.currentTimeMillis() - start));
        }
        double avg = list.stream().mapToLong(a -> a).average().getAsDouble();
        System.out.println(String.format("testReadLocalVar,test times %d, every calc limit %d, avg time %f.",
                TEST_TIMES, LIMIT, avg));
    }

//    testReadField,test times 100, every calc limit 10000000, avg time 18.000000.
    @Test
    public void testReadField() {
        List<Long> list = new ArrayList<>(TEST_TIMES);
        List<Long> datas = new ArrayList<>(TEST_TIMES);
        for (int j = 0; j < TEST_TIMES; j++) {
            long start = System.currentTimeMillis();
            long sum = 0;
            for (int i = 0; i < LIMIT; i++) {
                sum = sum + time + i +j;
            }
            datas.add(sum);
            list.add((System.currentTimeMillis() - start));
        }
        double avg = list.stream().mapToLong(a -> a).average().getAsDouble();
        System.out.println(String.format("testReadField,test times %d, every calc limit %d, avg time %f.",
                TEST_TIMES, LIMIT, avg));
    }

}
