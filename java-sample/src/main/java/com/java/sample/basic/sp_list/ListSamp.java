package com.java.sample.basic.sp_list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import com.java.sample.basic.sp_list.vo.Person;

@SuppressWarnings("unused")
public class ListSamp {
	public static void main(String[] args) {
		ListSamp samp = new ListSamp();
		samp.sort2();
	}

	/**
	 * Empty List 생성.
	 */
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

	public void indexOf() {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("A");
		list.add(null);
		list.add("");
		int index = list.indexOf("A");
		System.out.println(index);
	}

	public void sort() {
		List<String> list = new ArrayList<String>();
		list.add("A-2");
		list.add("B-1");
		list.add("A-3");
		list.add("D-5");
		list.add("E-0");
		
		list.sort((name1, name2) -> {
			int name1Index = Integer.parseInt(name1.substring(2));
			int name2Index = Integer.parseInt(name2.substring(2));

			return name1Index - name2Index;
		});
		
		System.out.println("End");
	}
	
	public void sort2() {
		List<Person> list = new ArrayList<Person>();

		for (int i = 0; i < 10; i++) {
			int no = (int) (Math.random() * 100);

			Person person = new Person();
			person.setName("Name-".concat(Integer.toString(no)));
			person.setPid(i);

			list.add(person);
		}

		list.sort(Comparator.comparing(Person::getName));

		list.forEach(p -> System.out.println(p.getName()));
		list.forEach(System.out::println);
		
		list.stream().forEach(System.out::println);

		System.out.println("End");
	}
}
