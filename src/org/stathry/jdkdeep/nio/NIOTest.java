/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.stathry.jdkdeep.nio;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * @author dongdaiming
 * @date 2018年1月7日
 */
public class NIOTest {
	
	@Test
	public void testGet() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		buffer.put((byte) 'a');
		buffer.put((byte) 'b');
		buffer.put((byte) 'c');

		 buffer.flip();

		System.out.println((char) buffer.get());
		System.out.println((char) buffer.get());
		System.out.println((char) buffer.get());
	}

	@Test
	public void testRead() throws IOException {
		FileInputStream in = new FileInputStream("/temp/a.txt");
		FileChannel ch = in.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		int n = 0;
		while (true) {
			buf.clear();
			n = ch.read(buf);
			String s = new String(buf.array(), "gbk");
			System.out.println(s);
			if (n == -1) {
				System.out.println("end");
				break;
			}
		}
		in.close();
	}

	@Test
	public void testWrite() throws IOException {
		FileOutputStream out = new FileOutputStream("/temp/nw.txt");
		FileChannel ch = out.getChannel();
		ByteBuffer buf = ByteBuffer.wrap("你好，nio".getBytes());
		ch.write(buf);
		out.close();
	}

	@Test
	public void testCopyNio1() throws IOException {
		// 间接缓冲区 2472/2409
		long start = System.currentTimeMillis();
		FileInputStream in = new FileInputStream("/temp/a.txt");
		FileOutputStream out = new FileOutputStream("/temp/a1.txt");
		FileChannel inch = in.getChannel();
		FileChannel outch = out.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(10240);
		int r = 0;
		while (true) {
			buf.clear();
			r = inch.read(buf);
			if (r == -1) {
				break;
			}
			buf.flip();
			outch.write(buf);
		}
		in.close();
		out.close();
		System.out.println(System.currentTimeMillis() - start);
	}

	@Test
	public void testCopyNio2() throws IOException {
		// 直接缓冲区 2199/2171
		long start = System.currentTimeMillis();
		FileInputStream in = new FileInputStream("/temp/a.txt");
		FileOutputStream out = new FileOutputStream("/temp/a1.txt");
		FileChannel inch = in.getChannel();
		FileChannel outch = out.getChannel();
		ByteBuffer buf = ByteBuffer.allocateDirect(10240);
		int r = 0;
		while (true) {
			buf.clear();
			r = inch.read(buf);
			if (r == -1) {
				break;
			}
			buf.flip();
			outch.write(buf);
		}
		in.close();
		out.close();
		System.out.println(System.currentTimeMillis() - start);
	}
	
	@Test
	public void testPutInts() throws IOException {
		// 直接缓冲区
		RandomAccessFile out = new RandomAccessFile("/temp/a5.txt", "rw");
		FileChannel outch = out.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < 10; i++) {
			s.append(i);
		}
		buf.put(s.toString().getBytes());
		buf.flip();
		outch.write(buf);
		out.close();
	}


}
