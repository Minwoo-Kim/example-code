package com.java.sample.util;

import java.text.MessageFormat;

public class MessageFormatSamp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void messageFormatEx() {
		String value = "{0} 값은 {1} Type이 아닙니다.";
		Object[] params = { 1, "String" };
		String result = MessageFormat.format(value, params);
		System.out.println(result);
	}
}
