package com.java.sample.test;

public class TestInvokeMain {

	public static void main(String[] args) {
		TestInvokeMain testInvokeMain = new TestInvokeMain();
		String value = testInvokeMain.a(null);
		
		System.out.println("a");
	}

	public String a(String p) {
		try {
			return "a";
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (p == null)
				return "f";
		}
	}

}
