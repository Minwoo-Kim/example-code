package com.example.code.java.java8;

import java.util.StringJoiner;

public class StringJoinerEx {
	public static void main(String[] args) {
		StringJoinerEx stringJoinerEx = new StringJoinerEx();
		stringJoinerEx.ex_1();

	}

	public void ex_1() {
		StringJoiner joiner = new StringJoiner("\n");
		joiner.add("A");
		joiner.add("B");
		joiner.add("C");
		joiner.add("D");
		joiner.add("E");

		System.out.println(joiner.toString());
	}
}