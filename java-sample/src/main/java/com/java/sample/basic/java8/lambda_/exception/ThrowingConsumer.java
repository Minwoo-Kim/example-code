package com.java.sample.basic.java8.lambda_.exception;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;
}