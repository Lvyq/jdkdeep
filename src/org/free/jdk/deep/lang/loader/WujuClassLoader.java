/*
 * Copyright © PING AN INSURANCE (GROUP) COMPANY OF CHINA ，LTD. All Rights Reserved
 */
package org.free.jdk.deep.lang.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author dongdaiming911@pingan.com
 * @date 2017年6月21日
 */
public class WujuClassLoader extends ClassLoader {

	private String classPath;

	public WujuClassLoader(String classPath) {
		super();
		this.classPath = classPath;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = loadClassFile(name);
		return defineClass(name, data, 0, data.length);
		
	}
	
	protected byte[] loadClassFile(String name)  {
		String name1 = name.replaceAll("\\.", "/");
		String path = classPath + "/" + name1 + ".class";
		
		try(FileInputStream fin = new FileInputStream(path)){
		int len = fin.available();
		byte[] data = new byte[len];
		fin.read(data);
		return data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
