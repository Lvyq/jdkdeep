/**
 * Copyright 2012-2016 free Co., Ltd.
 */
package org.stathry.jdkdeep.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * @author dongdaiming@free.com
 */
public class MathTest {

    @Test
    public void testCastNum() {
        // 小转大无精度丢失问题，无需强制转换
        short s = 1;
        int i = s;
        long l = i;
        // 大转小因为有精度丢失问题，所以需要转换强制转换
//        short s2 = i;
//        int i2 = l;
    }

    @Test
    public void testCastShort() {
        Short s0 = (1 == 1) ? 1 : 2;
        short s1 = (1 == 1) ? 1 : 2;
        short s2 = 3;
        short s3 = 1;
        // 大转小需要强转
//        s3 = s1 + 1;
        short s4 = 1;
        // +=自动做了转换
        s4 += 1;
        long i = 1 + s1;
    }

    @Test
    public void testDiv() {
        System.out.println(1/2);
        System.out.println(1.0/2);
        System.out.println(1/2.0);
    }

	@Test
	public void testMove() {
		// 无符号右移 忽略符号位，空位都以0补齐
        assertEquals(2, 8 >>> 2);
        assertEquals(0, 8 >>> 5);

        assertEquals(2, 8 >> 2);
        assertEquals(0, 8 >> 5);

        assertEquals(8, 4 << 1);
        assertEquals(16, 4 << 2);
	}

	@Test
	public void testXOR() {
        for (int i = 0; i < 100; i++) {
            System.out.println("7&" + i + "=" + (7 ^ i));
//            assertEquals((7 - i), 7 ^ i);
        }
	}

	@Test
	public void testAnd() {

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
	@Test(expected = NumberFormatException.class)
	public void test4() {
		System.out.println(Integer.parseInt("1 "));
	}
	
	@Test
	public void test5() {
//		System.out.println(Byte.MIN_VALUE);
//		System.out.println(Byte.MAX_VALUE);
//		int n = (int) (Math.pow(2, 7) - 1);
//		System.out.println(n);
//		 2^(7*8 + 7) - 1
//		long n2 = (long) (Math.pow(2, (7*8 + 7)) - 1L);
//		System.out.println(n2);
//		System.out.println(Long.MAX_VALUE);
		int n2 = (int) (Math.pow(2, (32 - 1)) - 1);
		System.out.println(n2);
		System.out.println(Integer.MAX_VALUE);
		assertEquals(Integer.MAX_VALUE, n2);
	}
	
}
