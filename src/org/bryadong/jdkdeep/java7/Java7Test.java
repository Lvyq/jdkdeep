package org.bryadong.jdkdeep.java7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2017年12月11日
 */
public class Java7Test {

	@Test
	public void testTryWith() throws IOException {
		try(InputStream in = new FileInputStream("/temp/zmxy.txt");) {
			byte[] b = new byte[1024];
			in.read(b);
			System.out.println(new String(b));
			
		}
	}
	
	@Test
	public void testTryFinally() throws IOException {
		InputStream in = null;
		try {
			in = new FileInputStream("/temp/zmxy.txt");
			byte[] b = new byte[1024];
			in.read(b);
			System.out.println(new String(b));
			
		} finally {
			if(in != null) {
				in.close();
			}
		}
	}
	
}
