/**
 * Copyright 2012-2016 free Co., Ltd.
 */
package org.free.jdk.deep.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * @author dongdaiming@free.com 2016年10月13日
 */
public class AutoCloseTest {

	@Test
	public void test1() {
		FileInputStream in = null;
		try {
			in = new FileInputStream("D:/usr/grms/jdbc.properties");
			in.read();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Test
	public void test2()  {
		try(FileInputStream in = new FileInputStream("D:/usr/grms/jdbc.properties"); 
				FileOutputStream out = new FileOutputStream("D:/usr/grms/jdbc.properties")) {
			in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3()  {
		FileInputStream in = null;
		try{
			 in = new FileInputStream("D:/usr/grms/jdbc.properties");
			in.read();
		} catch (Exception e) {
			
		}
	}
}
