package com.java.sample.basic.java8.lambda_.doublecolon;

import java.util.function.Function;

public class MacbookPro extends Computer {

	public MacbookPro(int age, String color) {
		super(age, color);
	}

	MacbookPro(Integer age, String color, Integer healty) {
		super(age, color, healty);
	}

	@Override
	public void turnOnPc() {
		System.out.println("MacbookPro turned on");
	}

	@Override
	public void turnOffPc() {
		System.out.println("MacbookPro turned off");
	}

	@Override
	public Double calculateValue(Double initialValue) {

		Function<Double, Double> function = super::calculateValue;
		final Double pcValue = function.apply(initialValue);
		System.out.println("First value is:" + pcValue);
		return pcValue + (initialValue / 10);

	}
}