package org.bryadong.jdkdeep.lang.finals;

import java.util.Random;

/**
 * TODO
 * @author dongdaiming
 * @date 2018年1月2日
 */
public class StaticFinalC2 {

//	 对N的访问会触发static block
//	public static final int N = new Random().nextInt(); 
//	 对N的访问不会触发static block
//	public static final int N = 6/3; 
	
	static {
		System.out.println("StaticFinalC2 static block");
	}
}
