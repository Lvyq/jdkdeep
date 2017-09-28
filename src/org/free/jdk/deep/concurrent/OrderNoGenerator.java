package org.free.jdk.deep.concurrent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TODO
 */
public class OrderNoGenerator {
    
    private long maxValue;
    private int maxLength;
    private String serialFormat;
    private String machineNo;
    private String datetimeFormat;

    private volatile AtomicLongBound no = new AtomicLongBound(0);

    public OrderNoGenerator(String datetimeFormat, String machineNo, long maxValue) {
        super();
        this.datetimeFormat = datetimeFormat;
        this.maxValue = maxValue;
        this.maxLength = String.valueOf(maxValue).length();
        this.serialFormat = "%0" + maxLength + "d";
        this.machineNo = machineNo;
    }
    
    /** 生成订单号（时间戳 + 递增序列 + 机器码） */
    public String generateOrderNo() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(datetimeFormat))
                + machineNo
                + String.format(serialFormat, no.incrementAndGet(maxValue));
    }
    
}
