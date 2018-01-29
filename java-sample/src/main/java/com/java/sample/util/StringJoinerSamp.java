package com.java.sample.util;

import java.util.StringJoiner;

public class StringJoinerSamp {
	public static void main(String[] args) {
		StringJoinerSamp stringJoinerEx = new StringJoinerSamp();
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