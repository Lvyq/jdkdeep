package org.bryadong.jdkdeep.classload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @date 2017年6月21日
 */
public class StathryClassLoader extends ClassLoader {

	private String custClassPath;

	public StathryClassLoader(String custClassPath) {
		super();
		this.custClassPath = custClassPath;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = loadClassFile(name);
		return defineClass(name, data, 0, data.length);
		
	}
	
	protected byte[] loadClassFile(String name)  {
		String name1 = name.replaceAll("\\.", "/");
		String path = custClassPath + "/" + name1 + ".class";
		
		try(FileInputStream fin = new FileInputStream(path)){
		int len = fin.available();
		byte[] data = new byte[len];
		fin.read(data);
		return data;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
