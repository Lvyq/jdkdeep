package org.free.jdk.deep.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 常见数据类型格式化
 * 
 * @author dongdaiming
 */
public class DataFormatUtils {

	private final SimpleDateFormat dateFormatter;
	private final DecimalFormat decimalFormatter;
	private static final int PRECISION = 22;
	private static final int MULTIPLIER = 1;

	public DataFormatUtils(String datePattern, int precision, int scale, int multiplier, RoundingMode mode) {
		dateFormatter = new SimpleDateFormat(datePattern);
		decimalFormatter = new DecimalFormat();
		decimalFormatter.setRoundingMode(mode);
		decimalFormatter.setGroupingUsed(false);
		decimalFormatter.setMaximumIntegerDigits(precision - scale);
		decimalFormatter.setMaximumFractionDigits(scale);
		decimalFormatter.setMinimumFractionDigits(scale);
		decimalFormatter.setMultiplier(multiplier);
	}
	
	public DataFormatUtils(String datePattern, int decimalScale, RoundingMode mode) {
		this(datePattern, PRECISION, decimalScale, MULTIPLIER, mode);
	}
	
	public DataFormatUtils(String datePattern, String decimalPattern, RoundingMode mode) {
		dateFormatter = new SimpleDateFormat(datePattern);
		decimalFormatter = new DecimalFormat();
		decimalFormatter.applyPattern(decimalPattern);
		decimalFormatter.setRoundingMode(mode);
	}

	public String format(Object data) {
		String value = "";
		if (data == null) {
			return "";
		} else if (data instanceof CharSequence) {
			value = ((CharSequence) data).toString().trim();
		} else if (data instanceof Date) {
			value = dateFormatter.format(data);
		} else if (data instanceof Calendar) {
			Calendar c1 = (Calendar) data;
			value = dateFormatter.format(c1.getTime());
		} else if (data instanceof Number) {
			if ((data instanceof Integer) || (data instanceof Long) || (data instanceof Byte) || (data instanceof Short)
					|| (data instanceof BigInteger)) {
				value = String.valueOf(data);
			} else {
				data = new BigDecimal(String.valueOf(data));
				value = decimalFormatter.format(data);
			}
		} else {
			value = String.valueOf(data);
		}
		return value;
	}

}
