package org.stathry.jdkdeep.lang;

import static org.junit.Assert.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

/**
 * TODO
 * 
 * @author dongdaiming
 * @date 2017年12月6日
 */
public class RandomTest {

    @Test
    public void testRandomRange() {
        SecureRandom r = new SecureRandom();
        int n ;
        for (int i = 0; i < 100_0000; i++) {
            n = r.nextInt(31);
            System.out.println(i + "\t" + n);
            if(n < 0) {
                System.out.println("negative number:" + i + "\t" + n);
                break;
            }
        }
    }

    @Test
    public void testRandomSeed() {
        Random r1 = new Random(888L);
        Random r2 = new Random(888L);
        int limit = 10;
        List<Integer> list1 = new ArrayList<>(limit);
        List<Integer> list2 = new ArrayList<>(limit);
        for (int i = 0; i < limit; i++) {
            list1.add(r1.nextInt(100));
            list2.add(r2.nextInt(100));
        }
        System.out.println(list1);
        System.out.println(list2);
    }

	@Test
	public void test1() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Math.random());
		}
	}

	@Test
	public void test2() {
		Random r = new Random();
		int limit = 1000;
		Set<Integer> set = new HashSet<>(limit);
		for (int i = 0; i < limit; i++) {
			set.add(r.nextInt(limit));
		}
		System.out.println(set.size());
	}

	@Test
	public void test3() {
		SecureRandom r = new SecureRandom();
		int limit = 10;
		Set<Integer> set = new HashSet<>(limit);
		for (int i = 0; i < limit; i++) {
			set.add(r.nextInt(limit));
		}
		System.out.println(set.size());
		System.out.println(set);
	}
	@Test
	public void test31() {
		SecureRandom r = new SecureRandom();
		String s = "";
		int n = 8;
		for(int i = 0; i < n; i++) {
			s += r.nextInt(10);
		}
		System.out.println(s);
	}

}
