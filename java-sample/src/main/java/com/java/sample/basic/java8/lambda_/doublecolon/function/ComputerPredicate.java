package com.java.sample.basic.java8.lambda_.doublecolon.function;

import com.java.sample.basic.java8.lambda_.doublecolon.Computer;

@FunctionalInterface
public interface ComputerPredicate {

	boolean filter(Computer c);

}