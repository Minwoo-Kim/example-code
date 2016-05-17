package com.example.code.spring.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExUtil {
	/**
	 * Class에 정의되어 있는 Field 목록 추출.(부모 객체의 Field 포함)
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Field> declaredFieldList(Class<?> clazz) {
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
