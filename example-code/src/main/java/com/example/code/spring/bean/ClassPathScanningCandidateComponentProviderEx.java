package com.example.code.spring.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

public class ClassPathScanningCandidateComponentProviderEx {
	public List<Class<?>> scanEntity(String basePackage) {
		List<Class<?>> classList = new ArrayList<>();

		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(RestController.class));

		StringUtils.tokenizeToStringArray(basePackage, ",");

		for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
			try {
				classList.add(Class.forName(bd.getBeanClassName()));
				// classList.add(ClassUtils.getClass(bd.getBeanClassName()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return classList;
	}

	public List<Class<?>> scanEntity_Two() {
		// Build pattern to lookup implementation class
		Pattern pattern = Pattern.compile(".*\\.*" + "Stamp");

		// Build classpath scanner and lookup bean definition
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new RegexPatternTypeFilter(pattern));

		String basePackage = "xyz.elidom.base.entity.basic";
		// scan from base package
		for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
			String beanName = bd.getBeanClassName();
			System.out.println(beanName);
		}
		return null;
	}
}