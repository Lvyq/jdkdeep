package org.stathry.jdkdeep.lang.ext;

import org.junit.Assert;
import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年12月29日
 */
public class ExtTest {
	
	@Test
	public void testExt() {
		Father f0 = new Father();
		f0.name1 = "";
		f0.name2 = "";
		f0.name3= "";
	}

    @Test
    public void testNoOver() {
        Father f = new Son();
        System.out.println(f.getName());
        Assert.assertEquals("fname", f.getName());
    }

}
