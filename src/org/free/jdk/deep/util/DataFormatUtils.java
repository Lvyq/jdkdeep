package org.free.jdk.deep.util;

import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    
    public DataFormatUtils(String datePattern, int maxDigits, int decimalScale, RoundingMode mode) {
        dateFormatter = new SimpleDateFormat(datePattern);
        decimalFormatter = new DecimalFormat();
        decimalFormatter.setRoundingMode(mode);
        decimalFormatter.setGroupingUsed(false);
        decimalFormatter.setMaximumIntegerDigits(maxDigits);
        decimalFormatter.setMaximumFractionDigits(decimalScale);
        decimalFormatter.setMaximumFractionDigits(decimalScale);
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

    public static void main(String[] args) {
    	DataFormatUtils c = new DataFormatUtils("yyyyMMdd", 20, 2, RoundingMode.HALF_UP);
    	
    	StringBuilder s = new StringBuilder("abc");
    	
//    	Assert.assertTrue("xyz".equals(c.format("xyz")));
//    	Assert.assertTrue("abc".equals(c.format(s)));
//    	Assert.assertTrue("20180111".equals(c.format(new Date())));
    	Assert.assertTrue("20180111".equals(c.format(Calendar.getInstance())));
    	Assert.assertTrue("0".equals(c.format(0)));
    	Assert.assertTrue("1".equals(c.format((short)1)));
    	Assert.assertTrue("1".equals(c.format((byte)1)));
    	Assert.assertTrue("1234567890123456".equals(c.format(1234567890123456L)));
    	Assert.assertTrue("0.46".equals(c.format(0.456)));
    	Assert.assertTrue("1.46".equals(c.format(1.456)));
    	Assert.assertTrue("134567.46".equals(c.format(134567.456)));
    	Assert.assertTrue("12345678901234.57".equals(c.format(12345678901234.567)));
	}
    
}
