package com.java.sample.lambda.function;

import java.util.function.Function;

import com.java.sample.lambda.doublecolon.DoubleConlonExam;

@SuppressWarnings("unused")
public class FunctionExam2 {

	public static void main(String[] args) {
		FunctionExam2 exam = new FunctionExam2();
		exam.example1();
		exam.example2();
	}

	public void example1() {
		Function<Integer, User> userFunction = User::new;
		User user = userFunction.apply(10);
		User user2 = userFunction.apply(5);

		Function<Integer, User[]> uesrArrayFunction = User[]::new;
		User[] userArray = uesrArrayFunction.apply(5);
	}

	public void example2() {
		Function<Integer, Integer> add = x -> x + 1;
		Function<String, String> concat = x -> x + 1;

		Integer two = add.apply(1); // yields 2
		String answer = concat.apply("0 + 1 = "); // yields "0 + 1 = 1"

		Function<Integer, Integer> addPlusOne = this::addPlusOne;
		Integer resultNumber = addPlusOne.apply(10);
		System.out.println(resultNumber);

		Function<String, String> addString = FunctionExam2::addString;
		String resultString = addString.apply("Value : ");
		System.out.println(resultString);
	}

	private Integer addPlusOne(Integer value) {
		return value + 1;
	}

	private static String addString(String value) {
		return value + 1;
	}
}

class User {

	Integer age;
	String name;

	User() {
	}

	User(Integer age) {
		this.age = age;
	}

	User(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}