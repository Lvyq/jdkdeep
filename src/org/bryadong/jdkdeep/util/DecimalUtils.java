package org.bryadong.jdkdeep.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 高精计算
 * 
 * @author dongdaiming
 * @date 2018年1月17日
 */
public class DecimalUtils {

	private static final int PRECISION = 22;
	private static final int SCALE = 2;
	private static final RoundingMode MODE = RoundingMode.HALF_UP;

	private final MathContext mc;
	private final DecimalFormat decimalFormatter;

	/**
	 * @param precision 总精度数
	 * @param mode 		舍入模式
	 */
	public DecimalUtils(int precision, int scale, RoundingMode mode) {
		mc = new MathContext(precision, mode);
		decimalFormatter = new DecimalFormat();
		decimalFormatter.setRoundingMode(mode);
		decimalFormatter.setGroupingUsed(false);
		decimalFormatter.setMaximumIntegerDigits(precision - scale);
		decimalFormatter.setMaximumFractionDigits(scale);
		decimalFormatter.setMinimumFractionDigits(scale);
	}

	public DecimalUtils() {
		this(PRECISION, SCALE, MODE);
	}

	public BigDecimal add(BigDecimal d1, BigDecimal d2) {
		return d1.add(d2, mc);
	}

	public BigDecimal subtract(BigDecimal d1, BigDecimal d2) {
		return d1.subtract(d2, mc);
	}

	public BigDecimal multiply(BigDecimal d1, BigDecimal d2) {
		return d1.multiply(d2, mc);
	}

	public BigDecimal divide(BigDecimal d1, BigDecimal d2) {
		return d1.divide(d2, mc);
	}

	public String format(Object n) {
		if (!(n instanceof BigDecimal)) {
			n = new BigDecimal(String.valueOf(n));
		}
		return decimalFormatter.format(n);
	}

}
