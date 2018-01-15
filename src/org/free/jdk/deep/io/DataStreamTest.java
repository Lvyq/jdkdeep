/*
 * Copyright © ORG.FREE ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.io;

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
public class DataStreamTest {

	@Test
	public void testWrite() throws IOException {
		File file = new File("/temp/data.txt");
		if(!file.exists()) {
			file.createNewFile();
		}
		try(DataOutputStream dout = new DataOutputStream(new FileOutputStream(file));) {
			dout.writeBoolean(true);
			dout.writeInt(22);
			dout.writeDouble(22.16);
			dout.writeBytes("java");
		}
		
	}
	@Test
	public void testRead() throws IOException {
		File file = new File("/temp/data.txt");
		try(DataInputStream din = new DataInputStream(new FileInputStream(file));) {
			System.out.println(din.readBoolean());
			System.out.println(din.readInt());
			System.out.println(din.readDouble());
			System.out.println(din.readLine());
		}
		
	}
	
}
