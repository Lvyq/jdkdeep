package org.stathry.jdkdeep.exception;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExceptionTypeShowCase {
	
	public void run1() {
		throw new RuntimeException();
	}
	
	public void run2() {
		throw new ClassCastException();
	}
	
	public void run3() {
		throw new NullPointerException();
	}
	
	public void run4() {
		throw new IllegalArgumentException();
	}
	
	public void check1() throws IOException {
		throw new IOException();
	}
	
	public void check2() throws SQLException {
		throw new SQLException();
	}
	
	public void check3() throws ParseException {
		new SimpleDateFormat().parse("");
	}

}
