package com.example.code.java.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@SuppressWarnings("unused")
public class ListEx {
	public void emptyList() {
		List<String> emptyList = Collections.<String>emptyList();
	}

	/**
	 * 수정이 불가능한 List 생성하기.
	 */
	public void unmodifiableList() {
		List<String> list = new ArrayList<String>();
		List<String> unmodifiableList = Collections.unmodifiableList(list);
	}

	/**
	 * List에 Array 객체 add All 하기.
	 */
	public void addAllArray() {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");

		String[] array = { "D", "E", "F" };

		Collections.addAll(list, array);
	}

	/**
	 * HashSet을 이용한, List 내의 중복 데이터 제거.
	 */
	public void listToHashTolist() {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add(null);
		list.add("");

		HashSet<String> set = new HashSet<String>();
		set.addAll(list);

		list.clear();
		list.addAll(set);
	}

	/**
	 * List 내의 특정 값을 모두 삭제하기.
	 */
	public void removeData() {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add(null);
		list.add("");

		list.removeAll(Collections.singleton("A"));
		list.removeAll(Collections.singleton(null));
	}
}
