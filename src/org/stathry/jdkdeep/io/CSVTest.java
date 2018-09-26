package org.stathry.jdkdeep.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVTest {
	
	public static void main(String[] args) throws IOException {
		File file = new File("/temp/temp.csv");
		if(file.exists()) {
			file.delete();
		}
		file.createNewFile();
		try(FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw);) {
			bw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
			bw.write("ab中文");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
