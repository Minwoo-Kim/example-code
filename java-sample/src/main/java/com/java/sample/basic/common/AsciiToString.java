/**
 * 
 */
package com.java.sample.basic.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author minu
 */
public class AsciiToString {
	public static void main(String[] args) {
		List<Character> charList = new ArrayList<Character>();

		// 0-9
		IntStream.range(48, 58).forEach(v -> charList.add((char) v));
		// A-Z
		IntStream.range(65, 91).forEach(v -> charList.add((char) v));
		// a-z
		IntStream.range(97, 123).forEach(v -> charList.add((char) v));

		int generateSize = 16;
		int size = charList.size();

		StringBuilder appender = new StringBuilder();
		for (int i = 0; i < generateSize; i++) {
			if (i > 0 && i % 4 == 0)
				appender.append("-");

			appender.append(charList.get((int) (Math.random() * size)));
		}

		System.out.println(appender.toString());
		
		String[] splitStr = "ABCDEFdsfdsafasdfG".split("(?<=\\G.{" + 4 + "})");
		String code = Arrays.stream(splitStr).collect(Collectors.joining("-"));
		System.out.println(code);
	}
}
