package com.example.code.java.basic;

import java.lang.reflect.Field;
import java.util.Date;

import org.apache.commons.lang.reflect.FieldUtils;

public class ReflectEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Object setValue(Class<?> clazz) {
		Object instance = null;
		Class<?> entityClass = clazz;
		try {
			instance = entityClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		do {
			try {
				Field[] fields = entityClass.getDeclaredFields();
				for (Field field : fields) {
					String fieldName = field.getName();
					Field accessField = FieldUtils.getDeclaredField(entityClass, fieldName, true);
					Object value = accessField.get(instance);
					
					Class<?> fieldType = field.getType();

					if (fieldType.isAssignableFrom(Long.class)) {
						// value = ValueUtil.toLong(value);
					} else if (fieldType.isAssignableFrom(Integer.class)) {
						// value = ValueUtil.toInteger(value);
					} else if (fieldType.isAssignableFrom(Double.class)) {
						// value = ValueUtil.toDouble(value);
					} else if (fieldType.isAssignableFrom(Date.class)) {
						value = new Date();
					}

					accessField.set(instance, value);
				}

				entityClass = entityClass.getSuperclass();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (!entityClass.isAssignableFrom(Object.class));
		return instance;
	}
}
