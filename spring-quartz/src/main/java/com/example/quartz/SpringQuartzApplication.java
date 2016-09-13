package com.example.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.quartz.util.BeanUtil;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SpringQuartzApplication {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringQuartzApplication.class, args);
		BeanUtil.setApplicationContext(applicationContext);
	}
}