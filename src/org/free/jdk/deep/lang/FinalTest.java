/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.lang;

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
  
public class FinalTest {  
	
    static{  
        System.out.println("FinalTest static block");  
    } 
	
    public static void main(String[] args) {  
        System.out.println(Final1.a);  
    }  
}  

