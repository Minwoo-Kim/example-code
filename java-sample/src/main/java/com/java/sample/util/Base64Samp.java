package com.java.sample.util;

import java.util.Base64;

public class Base64Samp {
	public static void main(String[] args) throws Exception {
		Base64Samp samp = new Base64Samp();

		String encode = samp.encode("Just Test");
		System.out.print(encode);

		String decode = samp.decode(encode);
		System.out.println(decode);
	}

	public String encode(String value) {
		return Base64.getEncoder().encodeToString(value.getBytes());
	}

	public String decode(String value) throws Exception {
		return new String(Base64.getDecoder().decode(value), "UTF-8");
	}
}
