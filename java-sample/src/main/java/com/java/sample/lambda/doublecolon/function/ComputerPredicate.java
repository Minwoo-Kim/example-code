package com.java.sample.lambda.doublecolon.function;

import com.java.sample.lambda.doublecolon.Computer;

@FunctionalInterface
public interface ComputerPredicate {

	boolean filter(Computer c);

}