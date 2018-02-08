/*
 * Copyright © stathry@126.com All Rights Reserved
 */
package org.stathry.jdkdeep.io;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

/**
 * @author dongdaiming
 * @date 2017年11月5日
 */
public class RandomAccessFileTest {

	@Test
	public void test() throws IOException {
		replaceLastChar("/temp/58/sh/rentalHousing.txt");
	}
	
	public static void replaceLastChar(String path) {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(path, "rw");
			raf.seek(raf.length()-1);
			raf.writeChar(' ');
		} catch (IOException e) {
			
		}
		finally {
			if(raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
