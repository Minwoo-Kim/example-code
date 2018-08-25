package com.example.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.*" })
public class ExampleSpringApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(ExampleSpringApplication.class, args);
		Environment env = configurableApplicationContext.getBean(Environment.class);
		String[] profiles = env.getActiveProfiles();
		System.out.println(profiles.toString());
	}
}