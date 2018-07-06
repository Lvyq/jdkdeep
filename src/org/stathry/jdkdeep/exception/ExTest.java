package org.stathry.jdkdeep.exception;

import org.junit.Test;

/**
 * TODO
 * Created by dongdaiming on 2018-06-07 14:03
 */
public class ExTest {

    private static final int limit1 = 10000_0000;

    @Test
    public void testFinallyReturn() {
        System.out.println(fm(1, 0));
        System.out.println(fm(12, 2));
    }

    private int fm(int n1, int n2) {
        int n = 0;
        try {
           return n = n1 / n2;
        } catch (Exception e) {
            throw e;
        }
        finally {
            return 1;
        }
    }

    @Test
    public void testTryCatch() {
        long begin = System.currentTimeMillis();
        int n = 0;
        try {
            for (int i = 0; i < limit1; i++) {
                n++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("testTryCatch:" + (System.currentTimeMillis() - begin) + ",n:" + n);
    }

    @Test
    public void testForTryCatch() {
        long begin = System.currentTimeMillis();
        int n = 0;
        for (int i = 0; i < limit1; i++) {
            try {
                n++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(n);
        System.out.println("testForTryCatch:" + (System.currentTimeMillis() - begin) + ",n:" + n);
    }
}
