package org.stathry.jdkdeep.util;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.Random;

/**
 * IP获取测试
 * Created by dongdaiming on 2018-07-20 15:23
 */
public class IPUtilsTest {

    @Test
    public void testAll() throws UnknownHostException {
        System.out.println(IPUtils.getCurIp());
        System.out.println(IPUtils.getCurIpTail());
    }

    //838644324
    //838644325
    @Test
    public void testIPHash() throws UnknownHostException {
        String ip1 = IPUtils.getCurIp();
        String ip2 = "192.168.12.168";
        String ip3 = "192.168.12.189";
        System.out.println(MessageFormat.format("ip:{0}, hashcode:{1}, dataCenterId:{2}", ip1, ip1.hashCode(), ip1.hashCode() % 30));
        System.out.println(MessageFormat.format("ip:{0}, hashcode:{1}, dataCenterId:{2}", ip2, ip2.hashCode(), ip2.hashCode() % 30));
        System.out.println(MessageFormat.format("ip:{0}, hashcode:{1}, dataCenterId:{2}", ip3, ip3.hashCode(), ip3.hashCode() % 30));
    }

}
