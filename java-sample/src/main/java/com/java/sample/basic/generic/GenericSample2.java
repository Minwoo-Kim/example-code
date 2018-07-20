package com.java.sample.basic.generic;

public class GenericSample2 {

	public static void main(String[] args) {
		GenericSample2 genericSample2 = new GenericSample2();
		System.out.println(genericSample2.parseInteger("123"));
	}

	public <T extends String> Integer parseInteger(T value) {
		return Integer.parseInt(value);
	}
}
