package com.java.sample.basic.common.sp_file;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FileRemoveSp {

	public void deleteServiceLog() {
		int period = 14;
		
		String values = "./logs/service, ./logs/http_invoke, ./logs";
		List<String> paths = Arrays.asList(values.split(","));

		for (String path : paths) {
			try {
				File logFiles = new File(path);
				if (!logFiles.exists())
					continue;

				if (logFiles.isDirectory())
					continue;

				/*
				 * 설정된 기한 이전에 생성된 파일 목록 추출.
				 */
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DAY_OF_MONTH, -period);
				Date baseDate = calendar.getTime();

				File[] files = logFiles.listFiles(new FileFilter() {
					@Override
					public boolean accept(File file) {
						return baseDate.compareTo(new Date(file.lastModified())) > 0;
					}
				});

				/*
				 * File 삭제
				 */
				for (File file : files) {
					try {
						file.delete();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
