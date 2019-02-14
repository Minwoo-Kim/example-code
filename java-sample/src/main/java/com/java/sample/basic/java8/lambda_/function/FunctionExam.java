package com.java.sample.basic.java8.lambda_.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import com.java.sample.basic.java8.lambda_.vo.Student;

public class FunctionExam {

	public static void main(String[] args) {
		System.out.println("학생 이름: ");
		printString(t -> t.getName());

		System.out.println("영어 점수: ");
		printInt(t -> t.getEnglishScore());

		System.out.println("수학 점수: ");
		printInt(t -> t.getMathScore());

		double englishAvg = avg(s -> s.getEnglishScore());
		System.out.println("영어 평균 점수: " + englishAvg);

		double mathAvg = avg(s -> s.getMathScore());
		System.out.println("영어 평균 점수: " + mathAvg);
	}

	private static List<Student> list = Arrays.asList(new Student("Jackie", 90, 65), new Student("Jolie", 100, 100));

	public static void printString(Function<Student, String> function) {

		for (Student std : list) {
			System.out.print(function.apply(std) + " ");
		}

		System.out.println();
	}

	public static void printInt(ToIntFunction<Student> function) {

		for (Student std : list) {
			System.out.print(function.applyAsInt(std) + " ");
		}

		System.out.println();
	}

	public static double avg(ToIntFunction<Student> function) {
		int sum = 0;

		for (Student std : list) {
			sum += function.applyAsInt(std);
		}

		double avg = (double) sum / list.size();

		return avg;
	}
}