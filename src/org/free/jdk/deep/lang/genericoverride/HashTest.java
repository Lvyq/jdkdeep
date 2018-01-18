package org.free.jdk.deep.lang.genericoverride;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2018年1月18日
 */
public class HashTest {

	@Test
	public void test() {
		System.out.println(new Object().hashCode());
		System.out.println(new Object().hashCode());
		System.out.println(new Object().hashCode());
	}

}
