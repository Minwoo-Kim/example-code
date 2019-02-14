package com.java.sample.basic.java8.files_;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FilesSamp {
	public static void main(String[] args) throws Exception {
		FilesSamp fs = new FilesSamp();
		fs.samp1();
	}

	public void samp1() throws Exception {
		Path path = Paths.get("/Users/minu/Imsi");

		Files.list(path).forEach(p -> {
			try {
				Optional.ofNullable(Files.readAllLines(p)).ifPresent(line -> line.stream().forEach(System.out::println));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		// Stream<File> files = paths.map(f -> f.toFile());
		// files.forEach(System.out::println);

		System.out.println("End");
	}
}
