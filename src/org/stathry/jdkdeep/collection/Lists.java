/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.stathry.jdkdeep.collection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月16日
 */
public class Lists {
	
	public static Integer[] findMaxIncSub(int[] src) {
		if (src == null || src.length < 1) {
			return null;
		}
		
		List<List<Integer>> subs = new ArrayList<>();
		List<Integer> sums = new ArrayList<>();
		for(int i = 0; i < src.length; i++) {
			List<Integer> sub = new ArrayList<>();
			sub.add(src[i]);
			int sum = src[i];
			for(int j = i + 1; j < src.length; j++) {
				if(src[j] - src[j-1] != 1) {
					break;
				}
				sub.add(src[j]);
				sum += src[j];
			}
			subs.add(sub);
			sums.add(sum);
		}
		
		int maxIndex = 0;
		int maxSum = sums.get(0);
		for(int i = 1, size = sums.size(); i < size; i++) {
			if(sums.get(i) > maxSum) {
				maxSum = sums.get(i);
				maxIndex = i;
			}
		}
		Integer[] maxSub = new Integer[subs.get(maxIndex).size()];
		return subs.get(maxIndex).toArray(maxSub);
	}
	
	@Test
	public void test1() {
		int[] a = {-3, -2, 0 ,22, 23, 1, 2, 3, 5,6};
		System.out.println(findMaxIncSub(a));
	}

}
