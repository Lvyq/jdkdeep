package org.stathry.jdkdeep.util;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.Random;

/**
 * IP获取
 * Created by dongdaiming on 2018-07-20 15:23
 */
public class IPUtils {

    private IPUtils() {}

    /**
     * 获取本机IP
     * @return
     * @throws UnknownHostException
     */
    public static String getCurIp() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * 获取本机IP末尾
     * @return
     * @throws UnknownHostException
     */
    public static String getCurIpTail() throws UnknownHostException {
        String a = InetAddress.getLocalHost().getHostAddress();
        return a.substring(a.lastIndexOf(".") + 1);
    }

    /**
     * 获取机器标识
     * @return
     * @throws UnknownHostException
     */
    public static int getHostFlagQuietly() {
        int flag;
        try {
            flag = InetAddress.getLocalHost().getHostAddress().hashCode();
        } catch (UnknownHostException e) {
            flag = new Random().nextInt();
        }
        return flag;
    }
}
