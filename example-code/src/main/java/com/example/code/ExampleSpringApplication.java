package com.example.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.*" })
public class ExampleSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExampleSpringApplication.class, args);
	}
}