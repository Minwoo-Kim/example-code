package com.java.sample.lambda.function;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class SupplierExam {
	public static void main(String[] args) {
		SupplierExam supplierExam = new SupplierExam();
		supplierExam.getNumber();
		supplierExam.getObject();
	}

	public void getNumber() {
		IntSupplier intSupplier = () -> {
			int num = (int) (Math.random() * 6) + 1;

			return num;
		};

		int num = intSupplier.getAsInt();
		System.out.println("눈의 수 : " + num);
	}

	public void getObject() {
		Supplier<String> supplier = () -> {
			int num = (int) (Math.random() * 6) + 1;
			return Integer.toString(num);
		};

		String value = supplier.get();
		System.out.println("눈의 수 : " + value);
	}
}
