package com.example.code.spring.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.core.annotation.AnnotationUtils;

import com.example.code.spring.ann.Column;
import com.example.code.spring.util.ExUtil;

public class AnnotationUtilsEx {
	public void getFieldAnnAtrInfoMap(Class<?> clazz) {
		List<Field> fields = ExUtil.declaredFieldList(clazz);

		for (Field field : fields) {
			Annotation columnAnn = field.getAnnotation(Column.class);
			if (columnAnn == null) {
				return;
			}
			
			Map<String, Object> colAnnInfoMap = AnnotationUtils.getAnnotationAttributes(columnAnn);

			colAnnInfoMap.forEach((k, v) -> {
				System.out.println(k + " : " + v);
			});
		}
	}
}