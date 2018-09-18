package com.java.sample.util;

import java.util.Base64;

public class Base64Util {
	public static String encode(String value) {
		return Base64.getEncoder().encodeToString(value.getBytes());
	}

	public static String decode(String value) throws Exception {
		return decode(value, "UTF-8");
	}

	public static String decode(String value, String charSet) throws Exception {
		return new String(Base64.getDecoder().decode(value), charSet);
	}
}
