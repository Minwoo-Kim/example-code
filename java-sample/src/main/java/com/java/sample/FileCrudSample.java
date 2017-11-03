package com.java.sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.StringJoiner;

public class FileCrudSample {
	public static void main(String[] args) throws Exception {
		FileCrudSample crudSample = new FileCrudSample();
		crudSample.create("/Users/minu/Downloads", "sample.txt", "Just Sample Data.");
		crudSample.read("/Users/minu/Downloads/sample.txt");
	}

	/**
	 * File 생성
	 * 
	 * @param dirPath
	 * @param fileName
	 * @param value
	 * @throws Exception
	 */
	public void create(String dirPath, String fileName, String value) throws Exception {
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
	public void update(String filePath, String value) throws Exception {
		this.update(new File(filePath), value);
	}

	public void update(File file, String value) throws Exception {
		String readData = this.read(file);

		StringJoiner contents = new StringJoiner("\n");
		if (readData != null && !readData.isEmpty()) {
			contents.add(readData);
		}

		contents.add(value);

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
			bufferedWriter.write(contents.toString());
		}
	}

	public void delete(String filePath) throws Exception {
		this.delete(new File(filePath));
	}

	public void delete(File file) throws Exception {
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
	public String read(String filePath) throws Exception {
		return this.read(new File(filePath));
	}

	public String read(File file) throws Exception {
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