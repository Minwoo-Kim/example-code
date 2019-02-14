package com.java.sample.basic.java8.optional_;

import java.util.Optional;

public class ZCustomSamp {

	String A;
	String B;
	String C;

	public static void main(String[] args) {
		ZCustomSamp samp = new ZCustomSamp();
		samp.optionalSamp1();
	}

	public void samp() {
		String a = this.A;
		if (a != null) {
			String b = this.B;
			if (b != null) {
				System.out.println("End");
			}
		}
	}

	public void optionalSamp1() {
		Optional<String> a = Optional.ofNullable(this.A);
		a.ifPresent(b -> {
			
			System.out.println("B : " + b);
		});
		
		Optional.ofNullable(this.A)
				.ifPresent(c -> {
					Optional.ofNullable(this.B)
							.ifPresent(p -> System.out.println("End"));
				});
		System.out.println("DD");
	}
}
