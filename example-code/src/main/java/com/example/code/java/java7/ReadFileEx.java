/**
 * 
 */
package com.example.code.java.java7;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Minu.Kim
 *
 */
public class ReadFileEx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadFileEx rfe = new ReadFileEx();
		rfe.readFile();

	}

	public void readFile() {
		String contextPath = System.getProperty("user.dir");
		String jsonDataPath = "/src/main/resources/seeds/json/";

		File file = new File(contextPath + jsonDataPath);

		if (file.isDirectory()) {
			File[] files = file.listFiles();

			for (File f : files) {
				try {
					String content = new String(Files.readAllBytes(Paths.get(f.toString())));
					System.out.println(content);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
