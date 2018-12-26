package org.stathry.jdkdeep.java9;

import static org.junit.Assert.*;

import java.util.Objects;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

/**
 * OptionalObjectsTest
 * 
 * @date 2018年2月6日
 */
public class OptionalObjectsTest {

    @Test
    public void testObjectsNullDefault() {
        // java9
//        Assert.assertEquals("abc",Objects.requireNonNullElse(null, "abc"));
//
//        Integer n = null, n1 = 888;
//        Assert.assertEquals(n1,Objects.requireNonNullElse(n, n1));
//
//        Assert.assertEquals("xyz",Objects.requireNonNullElse("xyz", "abc"));
    }

    @Test(expected = NullPointerException.class)
    public void testObjects() {
        Objects.requireNonNull(null, "obj");
    }

	@Test(expected = NullPointerException.class)
	public void testNotNull() {
        System.out.println(Optional.of("abc").get());
        Assert.assertEquals("abc", Optional.of("abc").get());

		Optional<Integer> n1 = Optional.of(null); // 为空直接NPT
	}

	@Test
	public void testOptionalNullDefault() {
		int n1 = Optional.ofNullable(666).orElse(0); // 为空返回0
		System.out.println(n1);
        Assert.assertEquals(666, n1);

        Integer o = null;
		int n2 = Optional.ofNullable(o).orElse(0);
		System.out.println(n2);
		Assert.assertEquals(0, n2);
	}

}
