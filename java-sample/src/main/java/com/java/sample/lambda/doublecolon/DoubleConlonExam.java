package com.java.sample.lambda.doublecolon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import com.java.sample.lambda.doublecolon.function.InterfaceComputer;
import com.java.sample.lambda.doublecolon.function.TriFunction;

@SuppressWarnings("unused")
public class DoubleConlonExam {
	public static void main(String[] args) {
		DoubleConlonExam doubleConlonExam = new DoubleConlonExam();
		doubleConlonExam.example1();
		doubleConlonExam.example2();
		doubleConlonExam.example3();
		doubleConlonExam.example4();
	}

	public void example1() {
		Comparator<Computer> comparator1 = (c1, c2) -> c1.getAge().compareTo(c2.getAge());
		Comparator<Computer> Comparator2 = Comparator.comparing(Computer::getAge);

		Computer computer1 = new Computer();
		computer1.setAge(10);
		computer1.setColor("Blue");

		Computer computer2 = new Computer();
		computer2.setAge(11);
		computer2.setColor("Red");

		Integer result1 = comparator1.compare(computer1, computer2);
		Integer result2 = Comparator2.compare(computer2, computer1);

		System.out.println(result1);
		System.out.println(result2);
	}

	public void example2() {
		List<Computer> inventory = Arrays.asList(new Computer(2015, "white", 35), new Computer(2009, "black", 65));
		inventory.forEach(ComputerUtils::repair);

		Computer c1 = new Computer(2015, "white");
		Computer c2 = new Computer(2009, "black");
		Computer c3 = new Computer(2014, "black");

		Arrays.asList(c1, c2, c3).forEach(System.out::print);
	}

	public void example3() {
		Computer c1 = new Computer(2015, "white", 100);
		Computer c2 = new MacbookPro(2009, "black", 100);
		
		List<Computer> inventory = Arrays.asList(c1, c2);
		inventory.forEach(Computer::turnOnPc);
	}

	public void example4() {
		InterfaceComputer c = Computer::new;
		Computer computer = c.create();

		TriFunction<Integer, String, Integer, Computer> c6Function = Computer::new;
		Computer c3 = c6Function.apply(2008, "black", 90);

		Function<Integer, Computer> computerCreator = Computer::new;
		Computer computer1 = computerCreator.apply(1);

		Function<Integer, Computer[]> computerCreator2 = Computer[]::new;
		Computer[] computerArray2 = computerCreator2.apply(5);

	}
}
