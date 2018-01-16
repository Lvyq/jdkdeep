/*
 * Copyright © ORG.FREE ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author dongdaiming
 * @date 2018年1月16日
 */
public class DataFormatUtilsTest {
	
	@Test(expected = NumberFormatException.class)
	public void testFormatDecimalEX() throws Exception {
		DataFormatUtils c = new DataFormatUtils("yyyyMMdd", 20, 2, RoundingMode.HALF_UP);
		StringBuilder s = new StringBuilder("abc");
		c.formatDecimal("xyz");
		c.formatDecimal(s);
	}

	@Test
	public void testFormat() {
		DataFormatUtils c = new DataFormatUtils("yyyyMMdd", 20, 2, RoundingMode.HALF_UP);
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
	
	}
	@Test
	public void testFormatDecimal() {
		DataFormatUtils c = new DataFormatUtils("yyyyMMdd", 20, 2, RoundingMode.HALF_UP);

		Assert.assertTrue("0.00".equals(c.formatDecimal(0)));
		Assert.assertTrue("1.00".equals(c.formatDecimal((short) 1)));
		Assert.assertTrue("1.00".equals(c.formatDecimal((byte) 1)));
		Assert.assertTrue("123456789012345678.00".equals(c.formatDecimal(123456789012345678L)));
		Assert.assertTrue("0.46".equals(c.formatDecimal(0.456)));
		Assert.assertTrue("1.46".equals(c.formatDecimal(1.456)));
		Assert.assertTrue("134567.46".equals(c.formatDecimal(134567.456)));
		Assert.assertTrue("12345678901234567890.57".equals(c.formatDecimal(new BigDecimal("12345678901234567890.567"))));
		Assert.assertTrue("34567890123456789012.57".equals(c.formatDecimal(new BigDecimal("1234567890123456789012.567"))));
	
	}
	@Test
	public void testCustomFormat() {
		DecimalFormat f = new DecimalFormat();
		f.setGroupingUsed(false);
		f.setMultiplier(100);
		Assert.assertTrue(f.format(123.456).equals("12345.6"));
		f.setMultiplier(1);
		Assert.assertTrue(f.format(123.456).equals("123.456"));
//		f.applyPattern("\u00A4");
//		f.setCurrency(Currency.getInstance(Locale.CHINA));
//		f.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.CHINA));
//		Assert.assertTrue("￥1000".equals(f.format(1000)));
//		f.setCurrency(Currency.getInstance(Locale.US));
//		f.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
//		Assert.assertTrue("$1000".equals(f.format(1000)));
		f.setDecimalSeparatorAlwaysShown(true);
		Assert.assertTrue("12345.".equals(f.format(12345)));
		f.setDecimalSeparatorAlwaysShown(false);
		Assert.assertTrue("12345".equals(f.format(12345)));
//		f.setNegativePrefix("负p");
//		f.setNegativeSuffix("负s");
//		Assert.assertTrue("负p123负s".equals(f.format(-123)));
		f.setParseIntegerOnly(true);
		Assert.assertTrue("0".equals(f.format(-0)));
		f.setParseIntegerOnly(false);
		f.setParseBigDecimal(false);
		System.out.println(f.format(-0));
		Assert.assertTrue("-0".equals(f.format(-0)));
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
	

}
