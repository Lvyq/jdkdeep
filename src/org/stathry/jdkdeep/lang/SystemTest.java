package org.stathry.jdkdeep.lang;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

/**
 * TODO
 * 
 * @date 2018年2月1日
 */
public class SystemTest {

    @Test
    public void testReadProp() {
        System.out.println(System.getProperty("Log4jContextSelector"));
    }

    @Test
    public void testSecurityManager() {
        System.out.println(System.getSecurityManager());
    }

	@Test
	public void testProps() {
		Properties props = System.getProperties();
		for (Entry<Object, Object> p : props.entrySet()) {
			System.out.println(p.getKey() + "===" + p.getValue());
		}
	}

	@Test
	public void testEnv() {
		Map<String, String> es = System.getenv();
		for (Entry<String, String> e : es.entrySet()) {
			System.out.println(e.getKey() + "===" + e.getValue());
		}
	}

}
