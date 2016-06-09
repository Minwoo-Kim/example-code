/**
 * 
 */
package com.example.code.java.basic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Minu.Kim
 *
 */
public class FileEx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileEx rfe = new FileEx();
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
	
	/**
	 * File 내용 불러오기 실행. 
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String readClassPathResource(String path) throws IOException {
		return IOUtils.toString(new ClassPathResource(path.trim()).getInputStream(), "UTF-8");
	}

	/**
	 * File 생성.
	 * 
	 * @param module
	 * @param entity
	 * @param content
	 */
	public void createFile(String dirPath, String fileName, String content) {
		// Directory 생성
		File dir = new File(dirPath);
		if (!dir.exists())
			dir.mkdirs();

		if (!dirPath.endsWith(File.separator))
			dirPath += File.separator;

		File file = new File(dirPath + fileName);
		try (FileWriter fileWriter = new FileWriter(file); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
			bufferedWriter.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
