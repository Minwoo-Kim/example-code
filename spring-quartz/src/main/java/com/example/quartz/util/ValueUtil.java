/**
 * 
 */
package com.example.quartz.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Value 빈값 체크, 검증, 변환등의 데이터 처리를 위한 UTIL
 * 
 * @author Minu.Kim
 */
@SuppressWarnings("unchecked")
public class ValueUtil {

	/**
	 * Data의 빈값 여부 Check. 값이 비어있을 경우 DefaultValue를 Return
	 * 
	 * @param data
	 * @param defaultValue
	 * @return
	 */
	public static <T> T checkValue(Object data, T defaultValue) {
		return !isEmpty(data) ? (T) data : defaultValue;
	}

	/**
	 * Check Empty
	 * 
	 * @param data
	 * @return
	 */
	public static boolean isEmpty(Object data) {
		if (data == null) {
			return true;
		} else if (data instanceof String || data instanceof StringBuffer) {
			String str = data.toString().trim();
			return str.isEmpty() || str.equalsIgnoreCase("null");
		} else if (data instanceof Object[]) {
			return ((Object[]) data).length == 0;
		} else if (data instanceof Collection<?>) {
			return ((Collection<?>) data).isEmpty();
		} else if (data instanceof Map<?, ?>) {
			return ((Map<?, ?>) data).isEmpty();
		}
		return false;
	}

	/**
	 * Check not Empty
	 * 
	 * @param data
	 * @return
	 */
	public static boolean isNotEmpty(Object data) {
		return !isEmpty(data);
	}

	/**
	 * a Param과 b Param이 동일한지 확인.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isEqual(Object a, Object b) {
		if (a == null && b == null) {
			return true;
		} else if (a == null || b == null) {
			return false;
		} else if (a.equals(b)) {
			return true;
		} else if (a instanceof List && b instanceof List) {
			List<Object> aList = (List<Object>) a;
			List<Object> bList = (List<Object>) b;
			if (aList.isEmpty() && bList.isEmpty()) {
				return true;
			} else if (aList.size() != bList.size()) {
				return false;
			}
			int i = 0;
			for (Object aObj : aList) {
				if (isNotEqual(aObj, bList.get(i++))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * a Param과 b Param이 동일하지 않은지 확인.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isNotEqual(Object a, Object b) {
		return !isEqual(a, b);
	}
}