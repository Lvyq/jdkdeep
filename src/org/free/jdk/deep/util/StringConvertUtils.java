package org.free.jdk.deep.util;

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
public class StringConvertUtils {

    private final SimpleDateFormat dateFormatter;
    private final DecimalFormat decimalFormatter;
    
    public StringConvertUtils(String datePattern, int maxDigits, int decimalScale, RoundingMode mode) {
        dateFormatter = new SimpleDateFormat(datePattern);
        decimalFormatter = new DecimalFormat();
        decimalFormatter.setRoundingMode(mode);
        decimalFormatter.setGroupingUsed(false);
        decimalFormatter.setMaximumIntegerDigits(maxDigits);
        decimalFormatter.setMaximumFractionDigits(decimalScale);
        decimalFormatter.setMaximumFractionDigits(decimalScale);
    }
    
    public String convert(Object data) {
        String value = "";
        if (data == null) {
            return "";
        } else if (data instanceof CharSequence) {
            value = ((CharSequence) data).toString();
        } else if ((data instanceof Date) || (data instanceof Calendar)) {
            value = dateFormatter.format(data);
        } else if (data instanceof Number) {
        	if((data instanceof Integer) || (data instanceof Long) || (data instanceof Byte) 
        			|| (data instanceof Short) || (data instanceof BigInteger)) {
        		value = String.valueOf(data);
        	} else {
        		value = decimalFormatter.format(data);
        	}
        } else {
            value = String.valueOf(data);
        }
        return value;
    }

//    public static void main(String[] args) {
//    	StringConvertUtils c = new StringConvertUtils("yyyyMMdd", 20, 2, RoundingMode.HALF_UP);
//    	StringBuilder s = new StringBuilder("abc");
//    	System.out.println(c.convert("xyz") + "___" + "xyz".equals(c.convert("xyz")));
//    	System.out.println(c.convert(s) + "__" + "abc".equals(c.convert(s)));
//    	System.out.println(c.convert(new Date()));
//    	System.out.println(c.convert(0));
//    	System.out.println(c.convert((short)1));
//    	System.out.println(c.convert((byte)1));
//    	System.out.println(c.convert(1234567890123456L));
//    	System.out.println(c.convert(0.456));
//    	System.out.println(c.convert(1.456));
//    	System.out.println(c.convert(134567.456));
//    	System.out.println(c.convert(12345678901234.567));
//	}
    
}
