package com.example.code.java.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class ListEx {
	public void emptyList() {
		List<String> emptyList = Collections.<String>emptyList();
	}

	public void unmodifiableList() {
		List<String> list = new ArrayList<String>();
		List<String> unmodifiableList = Collections.unmodifiableList(list);
	}
}
