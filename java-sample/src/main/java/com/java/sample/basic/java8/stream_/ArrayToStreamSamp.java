/**
 * 
 */
package com.java.sample.basic.java8.stream_;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Minu.Kim
 *
 */
public class ArrayToStreamSamp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayToStreamSamp arrayToStreamEx = new ArrayToStreamSamp();
		arrayToStreamEx.ex_1();
	}

	public void ex_1() {
		String[] array = { "a", "b", "c", "d", "e" };

		// Arrays.stream
		Stream<String> stream1 = Arrays.stream(array);
		stream1.forEach(x -> {
			System.out.println(x);
		});

		// Stream.of
		Stream<String> stream2 = Stream.of(array);
		stream2.forEach(x -> System.out.println(x));
	}
}