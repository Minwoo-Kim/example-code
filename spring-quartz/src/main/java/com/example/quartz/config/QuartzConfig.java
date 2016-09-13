package com.example.quartz.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

	@Autowired
	private DataSource dataSource;

	@Bean
	public SchedulerFactoryBean quartz() {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();

		bean.setAutoStartup(true);
		bean.setDataSource(dataSource);
		bean.setConfigLocation(new ClassPathResource("properties/quartz.properties"));

		return bean;
	}
}