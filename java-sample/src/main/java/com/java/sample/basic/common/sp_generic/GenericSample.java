package com.java.sample.basic.common.sp_generic;

public class GenericSample<T> {
	public GenericSample() {
	}

	public void genericTest(T value) {
		System.out.println(value);
	}
}

class CallGenericSample {
	public void callGenericSample() {
		GenericSample<String> gs = new GenericSample<String>();
		gs.genericTest("Generic!");
	}
}
