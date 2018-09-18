package com.java.sample.lambda.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.java.sample.lambda.vo.Student;

public class PredicateExam {

	public static void main(String[] args) {
		PredicateExam predicateExam = new PredicateExam();

		double maleAvg[] = predicateExam.avg(t -> t.getSex().equals("Male"));
		System.out.println("남자 평균 점수(영어, 수학)");

		for (double avg : maleAvg) {
			System.out.print(avg + " ");
		}
		System.out.println();

		double femaleAvg[] = predicateExam.avg(t -> t.getSex().equals("Female"));
		System.out.println("여자 평균 점수(영어, 수학)");

		for (double avg : femaleAvg) {
			System.out.print(avg + " ");
		}
		System.out.println();

	}

	public double[] avg(Predicate<Student> predicate) {
		int count = 0, engSum = 0, mathSum = 0;

		for (Student std : this.getList()) {
			if (predicate.test(std)) {
				count++;
				engSum += std.getEnglishScore();
				mathSum += std.getMathScore();
			}
		}

		double avg[] = { ((double) engSum / count), ((double) mathSum / count) };

		return avg;
	}

	private List<Student> getList() {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("Martin", 80, 90, "Male"));
		list.add(new Student("Jolie", 74, 88, "Female"));
		list.add(new Student("Sophie", 66, 100, "Female"));
		list.add(new Student("Pierre", 100, 78, "Male"));
		list.add(new Student("anne", 80, 90, "Female"));
		list.add(new Student("Paul", 42, 91, "Male"));
		list.add(new Student("cristianne", 99, 100, "Female"));
		list.add(new Student("Mcg", 100, 90, "Male"));

		return list;
	}
}
