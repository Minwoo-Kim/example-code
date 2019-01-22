package com.java.sample.basic.sp_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSamp {

	public static void main(String[] args) {
		new StreamSamp().samp1();
	}

	private void samp1() {
		{
			List<String> list = Arrays.asList("A", "B", "C");
			Stream<String> stream = list.stream();

			stream.forEach(System.out::println);
		}

		{
			Stream<String> stream = Stream.of("A", "B", "C");
			stream.forEach(System.out::println);
		}

		{
			IntStream stream = IntStream.range(1, 4);
			stream.forEach(System.out::println);
		}

	}

	private void samp2() {
		List<String> list = new ArrayList<String>();

		// filter
		{
			Stream<String> stream = Stream.of("apple", "banana", "cucumber");
			Stream<String> newStream = stream.filter(v -> v.length() > 5);
			newStream.forEach(System.out::println);
		}

		// map
		{
			Stream<String> stream = Stream.of("apple", "banana", "cucumber");
			stream.map(v -> v.concat("_s"))
					.forEach(System.out::println);
		}

		// peak
		{
			Stream<String> stream = Stream.of("apple", "banana", "cucumber");
			stream.peek(v -> list.add("peak"))
					.map(v -> list.add("map"));

		}

		// Match
		{
			int[] intArr = { 2, 4, 6, 8, 10, 12 };

			boolean result = Arrays.stream(intArr)
					.allMatch(a -> a % 2 == 0);
			System.out.println("모두 2의 배수 인가 ? " + result);

			result = Arrays.stream(intArr)
					.anyMatch(a -> a % 3 == 0);
			System.out.println("하나라도 3의 배수가 있는가 ? " + result);

			result = Arrays.stream(intArr)
					.noneMatch(a -> a % 5 == 0);
			System.out.println("5의 배수가 없는가 ? " + result);
		}
	}

	private void others() {
		{
			Stream<String> a = Stream.of("A", "B", "C");
			System.out.println(a.findFirst().get());

			List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
			myList.stream()
					.filter(s -> s.startsWith("c"))
					.map(String::toUpperCase)
					.sorted()
					.forEach(System.out::println);
		}

		{
			Stream.of("a1", "a2", "a3")
					.map(s -> s.substring(1))
					.mapToInt(Integer::parseInt)
					.max()
					.ifPresent(System.out::println);
		}

		{
			List<String> names = Arrays.asList("jeong", "pro", "jdk", "java");
			// 기존의 코딩 방식
			long count = 0;
			for (String name : names) {
				if (name.contains("o")) {
					count++;
				}
			}
			System.out.println("Count : " + count); // 2

			// 스트림 이용한 방식
			count = 0;
			count = names.stream().filter(x -> x.contains("o")).count();
			System.out.println("Count : " + count); // 2
		}

		{
			int[] intArr = { 5, 4, 3, 2, 1 };

			System.out.println("[peek()를 마지막에 호출한 경우]");
			Arrays.stream(intArr)
					.filter(a -> a % 2 == 0)
					.peek(n -> System.out.println(n)); // 동작하지 않음

			System.out.println("[최종 처리 메소드를 마지막에 호출한 경우]");
			int total = Arrays.stream(intArr)
					.filter(a -> a % 2 == 0)
					.peek(n -> System.out.println(n)) // 동작함
					.sum();
			System.out.println("총합: " + total);

			System.out.println("[forEach를 마지막에 호출한 경우]");
			Arrays.stream(intArr)
					.filter(a -> a % 2 == 0)
					.forEach(n -> System.out.println(n)); // 최종 메소드로 동작함
		}
	}
}
