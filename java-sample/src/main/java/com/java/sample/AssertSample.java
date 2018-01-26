package com.java.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class AssertSample {
	public static void main(String[] args) throws Exception {
		new AssertSample().sample(null);
	}

	public void sample(String value) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add(null);
		list.add("");
		
		
		list.removeAll(Collections.singleton("A"));
		list.removeAll(Collections.singleton(null));
		
		
		System.out.print("DD");
	}

}
