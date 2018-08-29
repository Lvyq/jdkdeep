package org.stathry.jdkdeep.util;

import java.util.concurrent.TimeUnit;

/**
 * 时间统计
 * Created by dongdaiming on 2018-08-07 20:35
 */
public class TimeCounter {

    private long begin = System.currentTimeMillis();

    public long begin() {
        return begin;
    }

    public long duration() {
        return System.currentTimeMillis() - begin;
    }

    public long duration(TimeUnit targetTimeUnit) {
        long duration = System.currentTimeMillis() - begin;
        switch (targetTimeUnit) {
            case MINUTES: duration = TimeUnit.MILLISECONDS.toMinutes(duration); break;
            case SECONDS: duration = TimeUnit.MILLISECONDS.toSeconds(duration); break;
            case MILLISECONDS: break;
            default: throw new IllegalArgumentException("illegal targetTimeUnit " + targetTimeUnit.name());
        }
        return duration;
    }

}
