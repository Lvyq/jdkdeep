package org.free.jdk.deep.math;

import static org.junit.Assert.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2018年1月5日
 */
public class NumberFormatTest {

	@Test
	public void test() {
		DecimalFormat format = new DecimalFormat();
		format.setGroupingUsed(false);
		format.setMaximumIntegerDigits(20);
		format.setMinimumFractionDigits(2);
		format.setMaximumFractionDigits(2);
		format.setRoundingMode(RoundingMode.HALF_UP);
		System.out.println(format.format(0));
		System.out.println(format.format(1));
		System.out.println(format.format(1234567890.12345));
		System.out.println(format.format(1234567890.12345f));
		System.out.println(format.format(1234567890123456.5432112345));
	}

}
