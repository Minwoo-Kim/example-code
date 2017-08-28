package com.example.code.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class IndexTestMain {
	private String ROOT_PATH = "C:/MachineData/MES";
	private String LOT_ID = "1";

	public static void main(String[] args) throws Exception {
		IndexTestMain itm = new IndexTestMain();

		long period = 10000;
		int limit = 100;
		int count = 0;

		do {
			itm.start("X100", "TOTAL_TEST.csv");
			count++;

			Thread.sleep(period);
		} while (count <= limit);
	}

	/**
	 * Batch Program 시작
	 * 
	 * @param machineName
	 * @param fileName
	 * @throws Exception
	 */
	public void start(String machineName, String fileName) throws Exception {
		String date = new SimpleDateFormat("yyyy_MM_dd").format(new Date());

		/**
		 * Total Data
		 */
		StringJoiner totalPath = new StringJoiner("/");
		totalPath.add(ROOT_PATH).add(machineName).add(date).add(LOT_ID);

		String totalData = "1," + machineName + ",1,2017_1_11,AM9:9:21,PASS,PASS,PASS,PASS,PASS,1,,H000021310,";
		String lineNo = this.refreshData(totalPath.toString(), fileName, totalData);

		/**
		 * Refresh Index File
		 */
		String indexDirPath = ROOT_PATH + "/" + machineName;
		String indexFileName = "EDC_" + date + ".idx";

		// Index File 내용
		StringJoiner contents = new StringJoiner(",");
		contents.add(totalPath.toString() + "/" + fileName);
		contents.add(lineNo);
		contents.add(new SimpleDateFormat("yyyy_MM_dd hh:mm:ss").format(new Date()));

		this.refreshData(indexDirPath, indexFileName, contents.toString());
	}

	/**
	 * File 생성 및 수정
	 * 
	 * @param dirPath
	 * @param fileName
	 * @param value
	 * @return 마지막 Line Number
	 * @throws Exception
	 */
	public String refreshData(String dirPath, String fileName, String value) throws Exception {
		File file = new File(dirPath, fileName);

		synchronized (file) {
			if (!file.exists()) {
				this.create(dirPath, fileName, value);
			} else {
				this.update(file, value);
			}

			return this.read(file)[1];
		}
	}

	/**
	 * File 내용 읽기
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public String[] read(File file) throws Exception {
		int line = 0;
		StringJoiner contents = new StringJoiner("\n");

		@SuppressWarnings("resource")
		RandomAccessFile raf = new RandomAccessFile(file, "r");

		String value;

		while ((value = raf.readLine()) != null) {
			contents.add(value);
			line++;
		}

		String[] retrunData = new String[2];
		retrunData[0] = contents.toString();
		retrunData[1] = Integer.toString(line);

		return retrunData;
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
	public void update(File file, String value) throws Exception {
		String[] readData = this.read(file);

		StringJoiner contents = new StringJoiner("\n");
		if (readData != null && !readData[0].isEmpty()) {
			contents.add(readData[0]);
		}
		contents.add(value);

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
			bufferedWriter.write(contents.toString());
		}
	}
}