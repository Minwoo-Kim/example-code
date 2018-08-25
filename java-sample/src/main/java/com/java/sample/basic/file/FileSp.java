package com.java.sample.basic.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Date;

public class FileSp {
	public static void main(String[] args) {
		FileSp fileSp = new FileSp();
		fileSp.listFile("./");
		System.out.println("END");
	}

	public File[] listFile(String rootPath) {
		File dir = new File(rootPath);
		if (!dir.exists() || !dir.isDirectory())
			return null;

		File[] remainderFiles = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File file, String name) {
				new Date(file.lastModified());
				System.out.println(name);
				return true;
			}
		});

		return remainderFiles;
	}
}