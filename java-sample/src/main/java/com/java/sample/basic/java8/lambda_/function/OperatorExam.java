package com.java.sample.basic.java8.lambda_.function;

import java.util.function.IntBinaryOperator;

public class OperatorExam {

	public static void main(String[] args) {
		int max = maxOrMin((a, b) -> a > b ? a : b);
		System.out.println("최대 값 : " + max);

		int min = maxOrMin((a, b) -> a <= b ? a : b);
		System.out.println("최소 값 : " + min);
	}

	private static int[] scores = { 100, 92, 81, 78, 88, 96, 55, 94 };

	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];

		for (int score : scores)
			result = operator.applyAsInt(result, score);

		return result;
	}
}