package com.example.code.spring.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.annotation.AnnotationUtils;

import com.example.code.spring.ann.Column;

public class AnnotationUtilsEx {
	public void getClassAnnAttribute(Class<?> clazz) {
		Column column = clazz.getAnnotation(Column.class);
		Map<String, Object> map = AnnotationUtils.getAnnotationAttributes(column);
		System.out.println(map.get("name"));
	}

	public void getFieldAnnAtrInfoMap(Class<?> clazz) {
		List<Field> fields = this.declaredFieldList(clazz);

		for (Field field : fields) {
			Annotation columnAnn = field.getAnnotation(Column.class);
			if (columnAnn == null) {
				return;
			}

			Map<String, Object> colAnnInfoMap = AnnotationUtils.getAnnotationAttributes(columnAnn);
			colAnnInfoMap.forEach((k, v) -> System.out.println(k + " : " + v));
		}
	}

	/**
	 * Class에 정의되어 있는 Field 목록 추출.(부모 객체의 Field 포함)
	 * 
	 * @param clazz
	 * @return
	 */
	private List<Field> declaredFieldList(Class<?> clazz) {
		List<Field> filedList = new ArrayList<Field>();
		Class<?> targetClass = clazz;
		do {
			Field[] fields = targetClass.getDeclaredFields();
			for (Field field : fields) {
				filedList.add(field);
			}

			targetClass = targetClass.getSuperclass();
		} while (targetClass != null && targetClass instanceof Object);

		return filedList;
	}
}