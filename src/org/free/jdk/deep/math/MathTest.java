/**
 * Copyright 2012-2016 free Co., Ltd.
 */
package org.free.jdk.deep.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.channels.SelectionKey;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.junit.Test;

/**
 * @author dongdaiming@free.com
 */
public class MathTest {

	@Test
	public void testAnd() {
		System.out.println(SelectionKey.OP_READ);
		System.out.println(SelectionKey.OP_WRITE);
		System.out.println(SelectionKey.OP_CONNECT);
		System.out.println(SelectionKey.OP_ACCEPT);
		System.out.println(SelectionKey.OP_READ & SelectionKey.OP_WRITE);
	}
	@Test
	public void testround() {
		System.out.println(Math.round(11.49));// 11
		System.out.println(Math.round(11.5));// 12
		System.out.println(Math.round(-11.51));// -12
		System.out.println(Math.round(-11.5));//-11
	}
	
	@Test
	public void testfloor() {
		System.out.println(Math.floor(11.5));
		System.out.println(Math.floor(-11.5));
		System.out.println(Math.floor(-11.51));
	}
	
	@Test
	public void testceil() {
		System.out.println(Math.ceil(11.5));
		System.out.println(Math.ceil(-11.5));
		System.out.println(Math.ceil(-11.51));
	}
	
	@Test
	public void testMathContext() {
		MathContext m1 = MathContext.DECIMAL32;
		MathContext m2 = MathContext.DECIMAL64;
		MathContext m3 = MathContext.DECIMAL128;
		BigDecimal n1 = new BigDecimal("123456789123456789.123456789", m1);
		BigDecimal n2 = new BigDecimal("123456789123456789.123456789", m2);
		BigDecimal n3 = new BigDecimal("123456789123456789.123456789", m3);
		System.out.println(n1.toPlainString());
		System.out.println(n2.toPlainString());
		System.out.println(n3.toPlainString());
		
		System.out.println();
		System.out.println();
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(n3);
	}
	@Test
	public void testMathContext2() {
		BigDecimal n1 = new BigDecimal(0).setScale(4, RoundingMode.HALF_EVEN);
		BigDecimal n2 = new BigDecimal("123456789123456789.123456789").setScale(8, RoundingMode.HALF_EVEN);
		BigDecimal n3 = new BigDecimal("123456789123456789.123456789").setScale(10, RoundingMode.HALF_EVEN);
		System.out.println(n1.toPlainString());
		System.out.println(n2.toPlainString());
		System.out.println(n3.toPlainString());
		
		System.out.println();
		System.out.println();
		System.out.println(n1);
		System.out.println(n2);
		System.out.println(n3);
	}
	@Test
	public void testMathContext3() {
		BigDecimal n1 = new BigDecimal("123456789123456789.123456789", MathContext.DECIMAL64).divide(BigDecimal.ZERO, 2, RoundingMode.HALF_EVEN);
		System.out.println(n1);
	}
	
	@Test
	public void test3() throws ParseException {
//		BigDecimal f1 = new BigDecimal("123456789123456789");
		DecimalFormat format = new DecimalFormat();
		format.setGroupingUsed(true);
		format.setGroupingSize(4);
//		System.out.println(format.format(f1));
		String s = format.format(1234567891L);
		System.out.println(s);
		format.setGroupingSize(3);
		System.out.println(format.parse(s));
		;
		
	}
	@Test
	public void test4() {
		System.out.println(Integer.parseInt("1 "));
	}
	
}
