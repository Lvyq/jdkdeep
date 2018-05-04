package org.stathry.jdkdeep.util;

import org.stathry.jdkdeep.concurrent.BoundAtomicLong;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderUtils {

    private static final int MAX_LENGTH = 64;
    private static final int MAX_PARAM_LENGTH = 20;
    private static final char DEFAULT_CHAR = '0';

    private final int length;
    private final OrderFormat orderFormat;
    private final String timeFormat;
    private final int sequenceLength;
    private final int userIdLength;
    private final BoundAtomicLong sequence;
    private final DateFormat DATE_FORMAT;

    public OrderUtils(int length, OrderFormat orderFormat, String timeFormat, int sequenceLength) {
        this(length, orderFormat, timeFormat, sequenceLength, 0);

    }

    public OrderUtils(int length, OrderFormat orderFormat, String timeFormat, int sequenceLength, int userIdLength) {
        valid(length, timeFormat, sequenceLength, userIdLength);
        this.length = length;
        this.orderFormat = orderFormat;
        this.timeFormat = timeFormat;
        this.sequenceLength = sequenceLength;
        this.userIdLength = userIdLength;
        DATE_FORMAT = new SimpleDateFormat(timeFormat);
        int bound = (int)Math.pow(10, sequenceLength) - 1;
        sequence = new BoundAtomicLong(0, bound);
    }

    public String order() {
        return order("");
    }
    public String order(String userId) {
        String order = "";
        String time = DATE_FORMAT.format(new Date());
        String ss = leftPad(String.valueOf(sequence.incrementWithBound()), sequenceLength);
        if(OrderFormat.TIME_SEQ == orderFormat) {
            order = String.format(orderFormat.format(), time, ss);
        } else if(OrderFormat.TIME_SEQ_USER == orderFormat) {
            String su = leftPad(userId, userIdLength);
            order = String.format(orderFormat.format(), time, ss, su);
        }
        Assert.isTrue(order.length() == length, "error length." );
        return order;
    }

    private String leftPad(String s, int length) {
        if(s.length() < length) {
            int n = length - s.length();
            StringBuilder b = new StringBuilder();
            for (int i = 0; i <n ; i++) {
                b.append(DEFAULT_CHAR);
            }
            b.append(s);
            return b.toString();
        }
        return s;
    }

    private void valid(int length, String timeFormat, int sequenceLength, int userIdLength) {
        Assert.isTrue((length > 0 && length <= MAX_LENGTH), "invalid length." );
        Assert.isTrue((timeFormat != null && timeFormat.length() > 0), "invalid timeFormat." );
        new SimpleDateFormat(timeFormat).format(new Date());
        Assert.isTrue((sequenceLength > 0 && sequenceLength <= MAX_PARAM_LENGTH), "invalid sequenceLength." );
        Assert.isTrue((userIdLength >= 0 && userIdLength <= MAX_PARAM_LENGTH), "invalid userIdLength." );
    }


    public static enum OrderFormat {
        TIME_SEQ("%s%s"),
        TIME_SEQ_USER("%s%s%s"),
        ;

        private String format;

        private OrderFormat(String format) {
            this.format = format;
        }

        public String format() {
            return format;
        }

    }

}
