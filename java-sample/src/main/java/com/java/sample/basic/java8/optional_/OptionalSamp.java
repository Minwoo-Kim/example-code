package com.java.sample.basic.java8.optional_;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.Function;
import java.util.stream.Stream;

import com.java.sample.basic.java8.optional_.vo.Student;

@SuppressWarnings("unused")
public class OptionalSamp {

	public static void main(String[] args) {
		OptionalSamp samp = new OptionalSamp();
		// samp.samp1();
		// samp.compareMapAndFlatMap();
		samp.filter();
	}

	public void samp1() {
		/*
		 * Optional 선언.
		 */
		OptionalInt optionalInt = OptionalInt.empty();
		OptionalDouble optionalDouble = OptionalDouble.empty();
		OptionalLong optionalLong = OptionalLong.empty();

		// 빈 Optional 생성.
		Optional<String> emptyObj = Optional.empty();

		// 타입에 해당하는 Optional 객체 생성. (Null 입력 시 에러 발생.)
		Optional<String> ofObj = Optional.of("Of Sample");

		// 내부적으로 값이 null 일 경우 Optional.empty(), 값이 존재 할 경우 Optional.of() 수행.
		Optional<String> ofNullableOjb = Optional.ofNullable("Nullable Sample");

		ofObj.ifPresent(System.out::println);
		ofNullableOjb.ifPresent(System.out::println);
		optionalInt.ifPresent(System.out::println);
		optionalDouble.ifPresent(System.out::println);
		optionalLong.ifPresent(System.out::println);

		/*
		 * ifPresent() : 값이 존재 할 경우 수행.
		 */
		{
			emptyObj.ifPresent(System.out::println);
			emptyObj.ifPresent(v -> {
				System.out.println(v);
			});
		}

		/*
		 * orElse() : 값이 존재하지 않을 경우 Default 값 설정.
		 * orElseGet() : 값이 존재하지 않을 경우 Default 값 설정.
		 */
		{
			String value1 = emptyObj.orElse("Sample1");
			String value2 = emptyObj.orElseGet(() -> "Sample2");
			String value3 = emptyObj.orElseGet(() -> {
				return "Sample3";
			});

			String value4 = emptyObj.orElseGet(String::new);

			print(value1, value2, value3, value4);
		}

		ofObj.ifPresent(System.out::println);
		ofNullableOjb.ifPresent(System.out::println);

		System.out.println(emptyObj.orElseThrow(RuntimeException::new));
	}

	public void compareMapAndFlatMap() {
		/*
		 * Map
		 */
		{

			Optional<String> stringOptional = Optional.ofNullable("hi");
			Optional<Integer> integerOptional = stringOptional.map(s -> s.length());
			Optional<Integer> integerOptional2 = stringOptional.map(new Function<String, Integer>() {
				@Override
				public Integer apply(String s) {
					return s.length();
				}
			});

			Optional<String> stringOptional2 = stringOptional.map(s -> " java");
		}

		/*
		 * FlatMap
		 */
		{
			Optional<String> stringOptional = Optional.ofNullable("hi");
			Optional<Integer> integerOptional = stringOptional.flatMap(s -> Optional.of(s.length()));
		}

		/*
		 * VS
		 */
		{
			String[][] data = new String[][] { { "1", "2" }, { "3", "4" } };

			// Map
			Stream<Stream<String>> map = Arrays.stream(data).map(x -> Arrays.stream(x));
			map.forEach(s -> s.forEach(System.out::println));

			// FlatMap
			Stream<String> flatmap = Arrays.stream(data).flatMap(x -> Arrays.stream(x));
			flatmap.forEach(System.out::println);
		}

		/*
		 * Debug
		 */
		{
			String[][] data = new String[][] { { "1", "2" }, { "3", "4" } };

			// Map
			Stream<Stream<String>> map = Arrays.stream(data).map(x -> {
				System.out.print(x);
				return Arrays.stream(x);
			});

			map.forEach(s -> {
				s.forEach(a -> {
					System.out.println(a);
				});
			});

			// FlatMap
			Stream<String> flatmap = Arrays.stream(data).flatMap(x -> {
				System.out.println(x);
				return Arrays.stream(x);
			});

			flatmap.forEach(v -> {
				System.out.println(v);
			});
		}

		System.out.println("End");
	}

	public void filter() {
		Integer year = 2016;
		Optional<Integer> yearOptional = Optional.of(year);
		boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
		System.out.print(is2016);

		boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
		System.out.print(is2017);
	}

	public void example() {
		Student student = new Student();

		Integer score = 0;
		if (student != null) {
			if (student.getName() != null) {
				if (student.getSex() != null) {
					score = student.getEnglishScore();
				}
			}
		}

		Optional<Student> op = Optional.empty();
		Optional<Student> a = op.map(s -> s);
		Optional<Student> b = op.flatMap(s -> Optional.of(s));

		System.out.println(score);
	}

	private void print(Object... o) {
		for (Object v : o)
			System.out.println(v);
	}
}
