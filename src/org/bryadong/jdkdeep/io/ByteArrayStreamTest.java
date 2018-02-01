/*
 * Copyright © ORG.FREE ，LTD. All Rights Reserved
 */
package org.bryadong.jdkdeep.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

/**
 * @author dongdaiming
 * @date 2018年1月15日
 */
public class ByteArrayStreamTest {

	@Test
	public void testReadFromBA() throws IOException {
		File file = new File("/temp/58/sh/rentalHousing.txt");
		int bufSize = 1024;
		byte[] buf = new byte[bufSize];
		int len = 0;
		byte[] data = null;
		try(FileInputStream fin = new FileInputStream(file); 
				ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			while((len = fin.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			data = out.toByteArray();
		}
		ByteArrayInputStream bin = new ByteArrayInputStream(data);
		buf = new byte[bufSize];
		while((len = bin.read(buf)) != -1) {
			System.out.println(new String(buf, 0 , len));
		}
	}
	@Test
	public void testWriteToBA() throws IOException {
		File file = new File("/temp/58/sh/rentalHousing.txt");
		int bufSize = 1024;
		byte[] buf = new byte[bufSize];
		int len = 0;
		try(FileInputStream fin = new FileInputStream(file); 
				ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			while((len = fin.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			System.out.println(new String(out.toByteArray()));
		}
	}
	
}
