package com.example.code.spring.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class ClassPathScanningCandidateComponentProviderEx {
	public List<Class<?>> scanEntity() {
		List<Class<?>> classList = new ArrayList<>();

		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(RestController.class));

		String basePackage = "com.example.code";
		Set<BeanDefinition> beanDefinitionSet = scanner.findCandidateComponents(basePackage);

		for (BeanDefinition bd : beanDefinitionSet) {
			try {
				classList.add(Class.forName(bd.getBeanClassName()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return classList;
	}

	public void scanAnnotation() throws Exception {
		List<Class<?>> classList = new ArrayList<>();

		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new AnnotationTypeFilter(RestController.class));

		String basePackage = "com.example.code";
		
		Set<BeanDefinition> beanDefinitionSet = scanner.findCandidateComponents(basePackage);
		for (BeanDefinition bd : beanDefinitionSet)
			classList.add(Class.forName(bd.getBeanClassName()));

		for (Class<?> clazz : classList) {
			Method[] methods = clazz.getMethods();
			for (Method m : methods) {
				Annotation annotation = m.getAnnotation(RequestMapping.class);
				Map<String, Object> attributeMap = AnnotationUtils.getAnnotationAttributes(annotation);
				
				attributeMap.forEach((k, v) -> System.out.println(k + " : " + v));
			}
		}
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