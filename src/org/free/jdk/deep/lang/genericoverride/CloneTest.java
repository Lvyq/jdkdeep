/*
 * Copyright © ORG.FREE ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.lang.genericoverride;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author dongdaiming
 * @date 2018年1月17日
 */
public class CloneTest {

	// Object.clone()是protected方法,要实现克隆必须重写该方法
	@Test
	public void testDateClone() {
		Date d1 = new Date();
		Date d2 = (Date) d1.clone();
		Assert.assertTrue(d1 != d2);
		Assert.assertTrue(d1.equals(d2));
		Assert.assertTrue(d1.compareTo(d2) == 0);
	}
	@Test(expected = CloneNotSupportedException.class)
	public void testCustClone() throws CloneNotSupportedException {
		C1 d1 = new C1();
		C1 d2 = (C1) d1.clone();
	}
	@Test()
	public void testCustClone2() throws CloneNotSupportedException {
		C2 d1 = new C2();
		C2 d2 = (C2) d1.clone();
		Assert.assertTrue(d1 != d2);
		Assert.assertTrue(d1.equals(d2));
//		Assert.assertTrue(d1.compareTo(d2) == 0);
	}
	
	
	private static class C1 {
		
		private long value = System.currentTimeMillis();
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			C1 c1 = (C1) super.clone();
			c1.value = value;
			return c1;
		}
		
	} 
	
	private static class C2 implements Cloneable {
		
		private long value = System.currentTimeMillis();
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			C2 c1 = (C2) super.clone();
			c1.value = value;
			return c1;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			}
			boolean b = false;
			if(obj instanceof C2) {
				b = ((C2)obj).value == value;
			}
			return b;
		}
		
	} 

}
