package org.stathry.jdkdeep.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * FieldAccessTimeTest
 *
 * @author dongdaiming
 * @date 18/05/25
 */
public class FieldAccessTimeTest {

    private static final long LIMIT = 1000_0000;
    private long count = 0;

    public long getCount() {
        return count;
    }

    @Test
    public void testGetFieldVSDefineVar() {
        long start = System.currentTimeMillis();
        long count = 0;
        long n = getCount();
        for (int j = 0; j < LIMIT; j++) {
            count += n;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(String.format("testGetFieldVSDefineVar1, count %d, times %d, limit %d", count, time, LIMIT));

        start = System.currentTimeMillis();
        for (int j = 0; j < LIMIT; j++) {
            count += getCount();
        }
        time = System.currentTimeMillis() - start;
        System.out.println(String.format("testGetFieldVSDefineVar2, count %d, times %d, limit %d", count, time, LIMIT));
    }

    @Test
    public void testCountByField() {
        long start = System.currentTimeMillis();
        for (int j = 0; j < LIMIT; j++) {
            count++;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(String.format("testCountByField, count %d, times %d, limit %d", count, time, LIMIT));
    }

    @Test
    public void testCountLocalVar() {
        long count = 0;
        long start = System.currentTimeMillis();
        for (int j = 0; j < LIMIT; j++) {
            count++;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(String.format("testCountLocalVar, count %d, times %d, limit %d", count, time, LIMIT));
    }

}
