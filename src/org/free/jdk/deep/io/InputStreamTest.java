package org.free.jdk.deep.io;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * TODO
 * @author dongdaiming
 * @date 2018年1月12日
 */
public class InputStreamTest {
	
	@Test
	public void testFileInputStream1() throws IOException {
		byte[] buf = new byte[8192];
		try(FileInputStream in = new FileInputStream("/temp/1.txt");) {
			while(in.read(buf) != -1) {
				System.out.println(new String(buf, "utf-8"));
			}
		}
	}
	@Test
	public void testByteArrayInputStream1() throws IOException {
		byte[] buf = new byte[8192];
		try(ByteArrayInputStream in = new ByteArrayInputStream("你好,Hello!".getBytes("utf-8"));) {
			while(in.read(buf) != -1) {
				System.out.println(new String(buf, "utf-8"));
			}
		}
	}
	@Test
	public void testByteArrayInputStream2() throws IOException {
		byte[] buf1 = new byte[8192];
		byte[] buf2 = new byte[8192];
		try(FileInputStream in = new FileInputStream("/temp/1.txt");
				ByteArrayInputStream ain = new ByteArrayInputStream(buf2);) {
		}
	}

}
