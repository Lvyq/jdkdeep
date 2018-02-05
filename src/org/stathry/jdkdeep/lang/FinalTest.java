/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.stathry.jdkdeep.lang;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月17日
 */
class Final1{  
    
    public static final int a = 6/3;  
      
    static{  
        System.out.println("Final1 static block");  
    }  
}  
class Final2{  
	
	public static final int a = 6/3;  
	
	static{  
		System.out.println("Final1 static block");  
	}  
}  
  
public class FinalTest {  
	
    static{  
        System.out.println("FinalTest static block");  
    } 
	
    public static void main(String[] args) {  
    	long start = System.currentTimeMillis();
       for(int i = 0; i < 1000000; i++) {
    	   m3();
       }  
       long t1 = System.currentTimeMillis() - start;
       System.out.println(t1);
       
       start = System.currentTimeMillis();
       for(int i = 0; i < 100000000; i++) {
    	   m4();
       }  
       long t2 = System.currentTimeMillis() - start;
       System.out.println(t2);
       System.out.println(t2 * 1.0 / t1);
    }  
    
    /**
	 * 
	 */
	private static void m1() {
		final String s1 = "s1";
		final String s2 = "s2";
		String s3 = s1 + s2;
	}
	private static void m2() {
		 String s1 = "s1";
		 String s2 = "s2";
		String s3 = s1 + s2;
	}
	private static final void m3() {
		 String s1 = "s1";
		 String s2 = "s2";
		String s3 = s1 + s2;
	}
	private static void m4() {
		String s1 = "s1";
		String s2 = "s2";
		String s3 = s1 + s2;
	}
}  

