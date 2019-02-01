package org.stathry.jdkdeep.classload;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;

import org.junit.Test;
import org.stathry.jdkdeep.compare.User;
import org.stathry.jdkdeep.util.Assert;

//import com.sun.javaws.Launcher;

/**
 * TODO
 * 
 * @date 2018年2月2日
 */
public class ClassLoadTest {

    @Test
    public void testGetClassResource() throws IOException {
        assertGetClassResource("rt.jar", "java/lang/Object.class");
        assertGetClassResource("junit-4.12.jar", "org/junit/Test.class");
    }

    private void assertGetClassResource(String jarName, String resourceName) throws IOException {
        Enumeration<URL> urls = ClassLoader.getSystemResources(resourceName);
        URL url;
        for (; urls.hasMoreElements(); ) {
            url = urls.nextElement();
            System.out.println(url);
            Assert.isTrue(url.getPath().contains(jarName));
        }
    }

    @Test
	public void testMyClassLoader() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StathryClassLoader loader = new StathryClassLoader("/temp/classpath");
		Class c = loader.loadClass("org.stathry.jdkdeep.classload.TempClass");
		Constructor con = c.getConstructor(String.class, String.class, String.class);
		Object o = con.newInstance("kongming", "17621166666", "620503199906062350");
		System.out.println(o.toString());
		System.out.println(getLoaderName(c));
	}

	@Test
	public void testPrintLoaderOfJavaClass() {
		System.out.println(getLoaderName(Object.class));
		System.out.println(getLoaderName(String.class));
	}

	@Test
	public void testPrintLoaderOfMyClass() {
		System.out.println(getLoaderName(this.getClass()));
		System.out.println(getLoaderName(User.class));
	}
	
//	@Test
//	public void testClassPath() {
//		URL[] urls = Launcher.getBootstrapClassPath().getURLs();
//		for (URL url : urls) {
//			System.out.println(url);
//		}
//	}

	@Test
	public void testPrintClassLoader() throws ClassNotFoundException {
		ClassLoader loader = Class1.class.getClassLoader();
		while (loader != null) {
			System.out.println(loader.getClass().getName() + ",parent:" + getParentName(loader));
			loader = loader.getParent();
		}
		
		System.out.println();
		System.out.println();
		ClassLoader loader2 = new StathryClassLoader("/temp/classpath");
		while (loader2 != null) {
			System.out.println(loader2.getClass().getName() + ",parent:" + getParentName(loader2));
			loader2 = loader2.getParent();
		}
	}
	
	public String getLoaderName(Class<?> c) {
		String lname = "";
		ClassLoader loader = c.getClassLoader();
			lname = loader == null ? null : loader.getClass().getName();
		return lname;
	}

	/**
	 * @param loader
	 * @return
	 */
	private String getParentName(ClassLoader loader) {
		String name = "";
		if (loader.getParent() != null) {
			Class<?> c = loader.getParent().getClass();
			name = c == null ? null : c.getName();
		}
		return name;
	}

	private static class Class1 {

	}

}
