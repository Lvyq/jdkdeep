package org.stathry.jdkdeep.lang;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * TODO
 * @date 2018年2月7日
 */
public class NullTest {

    @Test
    public void testNullEqual() {
        Object o1 = null, o2 = null;
        Assert.assertEquals(o1, o2);
    }

	@Test(expected = NullPointerException.class)
	public void testNullIf() {
		Boolean v = null;
		if(v) {
			System.out.println(v);
		}
	}

    @Test
    public void testNullInstOf() {
        Boolean v = null;
        System.out.println( v instanceof Boolean);

        Date d = null;
        System.out.println( d instanceof Date);

        Object d2 = new Date();
        System.out.println();
    }

}
