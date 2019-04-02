package org.stathry.jdkdeep.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * FinallyReturnTest
 *
 * @author dongdaiming(董代明)
 * @date 2019-03-01 13:29
 */
public class FinallyReturnTest {

    public int finallyReturn() {
        try {
            return 666;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return 888;
        }
    }

    @Test
    public void testFinallyReturn() {
        int n = finallyReturn();
        System.out.println(n);
        Assert.assertEquals(888, n);
    }

}
