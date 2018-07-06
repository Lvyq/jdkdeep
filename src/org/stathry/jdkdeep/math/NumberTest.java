/*
 * Copyright © ORG.FREE ，LTD. All Rights Reserved
 */
package org.stathry.jdkdeep.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.stathry.jdkdeep.util.DataFormatUtils;
import org.stathry.jdkdeep.util.DecimalUtils;

/**
 * @author dongdaiming
 * @date 2018年1月16日
 */
public class NumberTest {

    @Test
    public void testShortRange() {
        System.out.println(Short.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }

    @Test
    public void testDoubleRange() {
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        DecimalFormat f = new DecimalFormat();
        f.setGroupingUsed(false);
        f.setParseIntegerOnly(true);
        System.out.println(f.format(Double.MAX_VALUE));
        double d1 = Math.pow(10, 308);
        System.out.println(f.format(d1));
        double d2 = Math.pow(10, 309);
        System.out.println(f.format(d2));
        Assert.assertTrue(Double.MAX_VALUE > Math.pow(10, 308));
        Assert.assertTrue(Double.MAX_VALUE < Math.pow(10, 309));
    }

    // Infinite表示无穷, finite表示有穷|有限
    @Test
    public void testNumRange() {
        double d = Math.sqrt(-2);
        System.out.println(d);
        Assert.assertTrue(Double.isNaN(d));
        // NaN与任何数字都不相等,NaN != NaN
        Assert.assertFalse(Double.NaN == d);
        Assert.assertFalse(Double.NaN == Double.NaN);

        float f = (float) d;
        Assert.assertTrue(Float.isNaN(f));

        double inf = 1.0 / 0.0;
        System.out.println(inf);
        System.out.println(Double.isInfinite(inf));
        Assert.assertTrue(Double.isInfinite(inf));

        double fin = -1.0 / 0.0;
        System.out.println(fin);
        System.out.println(Double.isInfinite(fin));

        Assert.assertTrue(Double.isFinite(0));
        Assert.assertTrue(Double.isFinite('0'));
        Assert.assertTrue(Double.isFinite(100));
    }

	@Test
	public void testDataFormat() {
//		DataFormatUtils c = new DataFormatUtils("yyyyMMdd", 20, 2, RoundingMode.HALF_UP);
		DataFormatUtils c = new DataFormatUtils("yyyyMMdd", "#0.00", RoundingMode.HALF_UP);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		StringBuilder s = new StringBuilder(" a bc ");
		Assert.assertTrue("xyz".equals(c.format(" xyz ")));
		Assert.assertTrue("a bc".equals(c.format(s)));
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		Assert.assertTrue(df.format(now).equals(c.format(new Date())));
		Assert.assertTrue(df.format(cal.getTime()).equals(c.format(cal)));
		Assert.assertTrue("0".equals(c.format(0)));
		Assert.assertTrue("1".equals(c.format((short) 1)));
		Assert.assertTrue("1".equals(c.format((byte) 1)));
		Assert.assertTrue("1234567890123456".equals(c.format(1234567890123456L)));
		Assert.assertTrue("0.46".equals(c.format(0.456)));
		Assert.assertTrue("1.46".equals(c.format(1.456)));
		Assert.assertTrue("134567.46".equals(c.format(134567.456)));
		Assert.assertTrue("12345678901234.57".equals(c.format(12345678901234.567)));
		Assert.assertTrue("12345678901234567890.57".equals(c.format(new BigDecimal("12345678901234567890.567"))));
		Assert.assertTrue("0.35".equals(c.format(0.345)));
	}
	
	@Test
	public void testNumberFormat() {
		DecimalUtils u = new DecimalUtils(22, 2, RoundingMode.HALF_UP);
		Assert.assertTrue("12345678901234567890.35".equals(u.format("12345678901234567890.345")));
		Assert.assertTrue("12345678901234567890.35".equals(u.format(new BigDecimal("12345678901234567890.345"))));
		Assert.assertTrue("0.00".equals(u.format(0)));
		Assert.assertTrue("12345.68".equals(u.format(12345.678)));
//		Assert.assertTrue("301353.1".equals(u.format(301353.05)));
//		Assert.assertTrue("301353.1".equals(u.format(new BigDecimal("301353.05"))));
	}
	
	@Test
	public void testDecimalCalc() {
		DecimalUtils u = new DecimalUtils(22, 2, RoundingMode.HALF_UP);
		Assert.assertTrue("2222.89".equals(u.format(u.add(new BigDecimal("1111.4444"), new BigDecimal("1111.4444")))));
		Assert.assertTrue("1111.44".equals(u.format(u.subtract(new BigDecimal("2222.8888"), new BigDecimal("1111.4444")))));
		Assert.assertTrue("24691357802469135780.69".equals(u.format(u.multiply(new BigDecimal("12345678901234567890.345"), new BigDecimal(2)))));
		Assert.assertTrue("1111.44".equals(u.format(u.divide(new BigDecimal("2222.8888"), new BigDecimal(2)))));
	}
	
	@Test
	public void testPreciseCalc() {
		
	}

	@Test
	public void testFormatByPattern() {
		// # 当该位存在数字时显示，不存在则不显示 0 该位不存在数字时显示为0
		DecimalFormat f = new DecimalFormat("#0.00");
		f.setRoundingMode(RoundingMode.HALF_UP);
		Assert.assertTrue("12345678901234567890.35".equals(f.format(new BigDecimal("12345678901234567890.345"))));
		Assert.assertTrue("1.00".equals(f.format(1)));
		Assert.assertTrue("0.35".equals(f.format(new BigDecimal("0.345"))));
		// double和float在计算机中存的是近似值，如果数字为无限小数则无法精确表示
		Assert.assertTrue("0.34".equals(f.format(0.345d)));
		Assert.assertTrue("0.34".equals(f.format(0.345f)));
		f.applyPattern("#0.00%");
		f.setRoundingMode(RoundingMode.HALF_UP);
		System.out.println(f.format(0.11245));
		Assert.assertTrue("11.24%".equals(f.format(0.11245)));
		Assert.assertTrue("11.25%".equals(f.format(new BigDecimal("0.11245"))));
	}
	
//	CARE
	@Test
	public void testBigDecimal() {
		double d = 301353.05;
		BigDecimal decimal = new BigDecimal(d);
		Assert.assertTrue("301353.0499999999883584678173065185546875".equals(decimal.toString()));
		Assert.assertTrue("301353.0".equals(decimal.setScale(1, RoundingMode.HALF_UP).toString()));
		
		BigDecimal decimal2 = new BigDecimal(String.valueOf(d));
		Assert.assertTrue("301353.05".equals(decimal2.toString()));
		Assert.assertTrue("301353.1".equals(decimal2.setScale(1, RoundingMode.HALF_UP).toString()));
	}

	@Test
	public void testCustomFormat() {
		DecimalFormat f = new DecimalFormat();
		f.setGroupingUsed(false);
		f.setMultiplier(100);
		Assert.assertTrue(f.format(123.456).equals("12345.6"));
		f.setMultiplier(1);
		Assert.assertTrue(f.format(123.456).equals("123.456"));
		// f.applyPattern("\u00A4");
		// f.setCurrency(Currency.getInstance(Locale.CHINA));
		// f.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.CHINA));
		// Assert.assertTrue("￥1000".equals(f.format(1000)));
		// f.setCurrency(Currency.getInstance(Locale.US));
		// f.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
		// Assert.assertTrue("$1000".equals(f.format(1000)));
		f.setDecimalSeparatorAlwaysShown(true);
		Assert.assertTrue("12345.".equals(f.format(12345)));
		f.setDecimalSeparatorAlwaysShown(false);
		Assert.assertTrue("12345".equals(f.format(12345)));
		// f.setNegativePrefix("负p");
		// f.setNegativeSuffix("负s");
		// Assert.assertTrue("负p123负s".equals(f.format(-123)));
	}

	@Test
	public void testCustomParse() throws ParseException {
		DecimalFormat f = new DecimalFormat();
		f.setParseIntegerOnly(true);
		Assert.assertTrue(new Long(123).equals(f.parse("123.456")));
		f.setParseIntegerOnly(false);
		f.setParseBigDecimal(true);
		Assert.assertTrue(new BigDecimal("123.456").equals(f.parse("123.456")));
	}

	@Test
	public void testBigInt() {
        System.out.println(Long.MAX_VALUE);
        BigInteger bl=new BigInteger(String.valueOf(Long.MAX_VALUE));
        BigInteger bInt = bl.multiply(BigInteger.TEN);
        System.out.println(bInt);
        Assert.assertTrue(bInt.compareTo(bl) > 0);
	}

}
