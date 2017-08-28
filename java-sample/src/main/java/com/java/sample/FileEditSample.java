package com.java.sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class FileEditSample {
	public static void main(String[] args) {
		String dirPath = "/Users/minu/Dev/Workspaces/sts-workspace";
		File dir = new File(dirPath);

		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (name.startsWith("elings"));
			}
		});

		for (File file : files) {
			FileEditSample.doModify(file);
		}
	}

	public static void doModify(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File innerFile : files) {
				FileEditSample.doModify(innerFile);
			}
		} else {
			String fileName = file.getName();
			if (!fileName.endsWith("elings.gradle"))
				return;

			try {
				StringJoiner stringJoiner = new StringJoiner("\n");

				String contents = new String(Files.readAllBytes(Paths.get(file.toString())));
				String[] datas = contents.split("\n");

				for (String data : datas) {
					data = data.replaceAll("!isTask(\"fix\")", "!isTask(\"fix\") && isTask(\"upload\")");
					stringJoiner.add(data);
				}

				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
				bufferedWriter.write(stringJoiner.toString());
				bufferedWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}