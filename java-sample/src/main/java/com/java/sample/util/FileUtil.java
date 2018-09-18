package com.java.sample.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class FileUtil {
	/**
	 * File 생성
	 * 
	 * @param dirPath
	 * @param fileName
	 * @param value
	 * @throws Exception
	 */
	public static void create(String dirPath, String fileName, String value) throws Exception {
		File directory = new File(dirPath);

		if (!directory.exists()) {
			directory.mkdirs();
		}

		try (FileOutputStream output = new FileOutputStream(dirPath + "/" + fileName)) {
			output.write(value.getBytes());
		}
	}

	/**
	 * File 내용 추가
	 * 
	 * @param file
	 * @param value
	 * @throws Exception
	 */
	public static void update(String filePath, String value) throws Exception {
		update(new File(filePath), value);
	}

	public static void update(File file, String value) throws Exception {
		String readData = read(file);

		StringJoiner contents = new StringJoiner("\n");
		if (readData != null && !readData.isEmpty()) {
			contents.add(readData);
		}

		contents.add(value);

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
			bufferedWriter.write(contents.toString());
		}
	}

	public static void delete(String filePath) throws Exception {
		delete(new File(filePath));
	}

	public static void delete(File file) throws Exception {
		// 위험해서 주석 처리.
		// if (file.isDirectory()) {
		// File[] files = file.listFiles();
		// for (File f : files)
		// this.delete(f);
		// }

		file.delete();
	}

	/**
	 * File 내용 읽기
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String read(String filePath) throws Exception {
		return read(new File(filePath));
	}

	public static String read(String dirPath, String fileName) throws Exception {
		return read(new File(dirPath, fileName));
	}

	public static String read(File file) throws Exception {
		if (!file.exists())
			return null;

		return new String(Files.readAllBytes(Paths.get(file.toString())), "UTF-8");
	}

	public static String readByRandomAccess(File file) throws Exception {
		StringJoiner contents = new StringJoiner("\n");

		@SuppressWarnings("resource")
		RandomAccessFile raf = new RandomAccessFile(file, "r");

		String value;

		while ((value = raf.readLine()) != null) {
			contents.add(value);
		}

		String result = contents.toString();
		System.out.println(result);

		return result;
	}
}