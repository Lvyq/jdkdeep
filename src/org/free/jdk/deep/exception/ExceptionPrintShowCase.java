package org.free.jdk.deep.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

public class ExceptionPrintShowCase {
	
	@Test
	public void readFile() {
		try {
			new FileInputStream(new File("jls"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void parent() {
		child();
	}

	private void child() {
		int n = 1 / 0;
	}
}
