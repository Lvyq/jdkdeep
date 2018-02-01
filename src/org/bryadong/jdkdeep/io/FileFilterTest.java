/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.bryadong.jdkdeep.io;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

import org.junit.Test;

/**
 * @author dongdaiming
 * @date 2018年1月14日
 */
public class FileFilterTest {
	
	@Test
	public void test1() {
		File dir = new File("/temp");
		File[] files = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(".txt");
			}
		});
		for(File f : files) {
			System.out.println(f.getAbsolutePath() + "\t" + f.getPath());
		}
	}
	@Test
	public void test2() {
		File dir = new File("/temp/class");
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".class");
			}
		});
		for(File f : files) {
			System.out.println(f.getAbsolutePath() + "\t" + f.getPath());
		}
	}
	@Test
	public void test3() {
		File dir = new File("/temp/class");
		File[] files = dir.listFiles((base, name) -> name.endsWith(".class"));
		for(File f : files) {
			System.out.println(f.getAbsolutePath() + "\t" + f.getPath());
		}
	}

}
