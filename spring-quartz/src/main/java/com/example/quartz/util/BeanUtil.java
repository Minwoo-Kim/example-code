package com.example.quartz.util;

import org.springframework.context.ApplicationContext;

public class BeanUtil {
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public static void setApplicationContext(ApplicationContext applicationContext) {
		BeanUtil.applicationContext = applicationContext;
	}
	
	public static <T> T getBean(Class<T> claszz) {
		return getApplicationContext().getBean(claszz);
	}
}
