package org.free.jdk.deep.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;

import org.junit.Assert;

/**
 * 常见数据类型格式化
 * 
 * @author dongdaiming
 */
public class DataFormatUtils {

	private final SimpleDateFormat dateFormatter;
	private final DecimalFormat decimalFormatter;
	private final String CURRENCY = "CNY";

	public DataFormatUtils(String datePattern, int maxDigits, int decimalScale, RoundingMode mode) {
		dateFormatter = new SimpleDateFormat(datePattern);
		decimalFormatter = new DecimalFormat();
		decimalFormatter.setRoundingMode(mode);
		decimalFormatter.setGroupingUsed(false);
		decimalFormatter.setMaximumIntegerDigits(maxDigits);
		decimalFormatter.setMaximumFractionDigits(decimalScale);
		decimalFormatter.setMinimumFractionDigits(decimalScale);
		decimalFormatter.setCurrency(Currency.getInstance(CURRENCY));
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
				value = decimalFormatter.format(data);
			}
		} else {
			value = String.valueOf(data);
		}
		return value;
	}

	public String formatDecimal(Object data) {
		String value = "";
		data = data == null ? 0 : data;
		if (data instanceof CharSequence) {
			value = ((CharSequence) data).toString().trim();
			data = new BigDecimal(value);
		}
		if (data instanceof Number) {
			value = decimalFormatter.format(data);
		} else {
			throw new IllegalArgumentException(value);
		}
		return value;
	}

}
