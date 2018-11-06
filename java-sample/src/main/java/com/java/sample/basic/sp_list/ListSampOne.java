package com.java.sample.basic.sp_list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import com.java.sample.basic.sp_list.vo.Person;

public class ListSampOne {
	public static void main(String[] args) {
		ListSampOne demo = new ListSampOne();

		List<Person> list = new ArrayList<>();
		list.add(new Person(1, "Mahesh"));
		list.add(new Person(2, "Ram"));
		list.add(new Person(3, "Krishna"));

		demo.forEachDemo(list);
		demo.removeIfDemo(list);
		demo.replaceAllDemo(list);
		demo.sortDemo(list);
	}

	private void forEachDemo(List<Person> list) {
		Consumer<Person> style = p -> System.out.println("id:" + p.getPid() + ", Name:" + p.getName());
		list.forEach(style);

		Consumer<String> consumerString = value -> System.out.println(value);
		consumerString.accept("Arpit");
	}

	private void removeIfDemo(List<Person> list) {
		Consumer<Person> style = (Person p) -> System.out.println("id:" + p.getPid() + ", Name:" + p.getName());

		System.out.println("---Before delete---");
		list.forEach(style);

		int pid = 2;
		Predicate<Person> personPredicate = p -> p.getPid() == pid;
		list.removeIf(personPredicate);

		System.out.println("---After delete---");
		list.forEach(style);

		System.out.println("---End---");
	}

	private void replaceAllDemo(List<Person> list) {
		Consumer<Person> style = (Person p) -> System.out.println("id:" + p.getPid() + ", Name:" + p.getName());
		System.out.println("---Before replaceAll---");
		list.forEach(style);

		UnaryOperator<Person> unaryOpt = pn -> modifyName(pn);
		list.replaceAll(unaryOpt);
		System.out.println("---After replaceAll---");
		list.forEach(style);

	}

	private void sortDemo(List<Person> list) {
		Consumer<Person> style = (Person p) -> System.out.println("id:" + p.getPid() + ", Name:" + p.getName());

		System.out.println("---Before Sorting---");
		list.forEach(style);

		list.sort((m1, m2) -> {
			return m2.getPid() - m1.getPid();
		});

		System.out.println("---After Sorting---");
		list.forEach(style);

	}
	
	private void sortDemo2(List<Person> list) {
		Consumer<Person> style = (Person p) -> System.out.println("id:" + p.getPid() + ", Name:" + p.getName());

		System.out.println("---Before Sorting---");
		list.forEach(style);

		list.sort(Comparator.comparing(Person::getPid));

		System.out.println("---After Sorting---");
		list.forEach(System.out::println);
	}

	private Person modifyName(Person p) {
		p.setName(p.getName().concat(" -Good"));
		return p;
	}
}