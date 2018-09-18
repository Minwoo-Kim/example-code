package com.java.sample.func;

import java.text.MessageFormat;

public class MessageFormatSamp {

	public static void main(String[] args) {
		new MessageFormatSamp().messageFormatEx();
	}

	public void messageFormatEx() {
		String value = "{0} 값은 {1} Type이 아닙니다.";
		Object[] params = { 1, "String" };
		String result = MessageFormat.format(value, params);
		System.out.println(result);
	}
}
