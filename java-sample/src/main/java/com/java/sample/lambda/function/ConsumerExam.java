package com.java.sample.lambda.function;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.ObjIntConsumer;

import com.java.sample.lambda.exception.ThrowingConsumer;

public class ConsumerExam {
	public static void main(String[] args) {
		Consumer<String> consumer = t -> System.out.println(t + "8");
		consumer.accept("자바");

		BiConsumer<String, String> biConsumer = (t, u) -> System.out.println(t + u);
		biConsumer.accept("자바", "8");

		DoubleConsumer doubleConsumer = d -> System.out.println("자바" + d);
		doubleConsumer.accept(8.0);

		ObjIntConsumer<String> objIntConsumer = (t, i) -> System.out.println(t + i);
		objIntConsumer.accept("자바", 8);

		Consumer<String> param = t -> System.out.println("Start");
		Consumer<String> result = lambdaWrapper(param);
		result.accept("End");
	}

	public static Consumer<String> lambdaWrapper(Consumer<String> consumer) {
		return i -> {
			try {
				consumer.accept(i);
				System.out.println(i);
			} catch (ArithmeticException e) {
				System.err.println("Arithmetic Exception occured : " + e.getMessage());
			}
		};
	}

	static <T, E extends Exception> Consumer<T> consumerWrapper(Consumer<T> consumer, Class<E> clazz) {
		return i -> {
			try {
				consumer.accept(i);
			} catch (Exception ex) {
				try {
					E exCast = clazz.cast(ex);
					System.err.println("Exception occured : " + exCast.getMessage());
				} catch (ClassCastException ccEx) {
					throw ex;
				}
			}
		};
	}

	public static <T> Consumer<T> throwingConsumerWrapper(ThrowingConsumer<T, Exception> throwingConsumer) {
		return i -> {
			try {
				throwingConsumer.accept(i);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		};
	}

	public static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(ThrowingConsumer<T, E> throwingConsumer, Class<E> exceptionClass) {
		return i -> {
			try {
				throwingConsumer.accept(i);
			} catch (Exception ex) {
				try {
					E exCast = exceptionClass.cast(ex);
					System.err.println("Exception occured : " + exCast.getMessage());
				} catch (ClassCastException ccEx) {
					throw new RuntimeException(ex);
				}
			}
		};
	}
}
