/**
 * 
 */
package com.spring.boot.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Value 빈값 체크, 검증, 변환등의  데이터 처리를 위한 UTIL 
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
	
	/**
	 * value가 checkValues에 포함되어 있는지 체크
	 * 
	 * @param value
	 * @param checkValues
	 * @return
	 */
	public static boolean isInclude(Object value, Object... checkValues) {
		for(Object checkValue : checkValues) {
			if(ValueUtil.isEqual(value, checkValue)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Convert Object To Pretty JSON String
	 * 
	 * @param item
	 * @return
	 */
	public static String toJsonString(Object item) {
		return toJsonString(item, true);
	}
	
	/**
	 * Convert Object To JSON String
	 * 
	 * @param item
	 * @param pretty
	 * @return
	 */
	public static String toJsonString(Object item, boolean pretty) {
		if(pretty) {
			return new GsonBuilder().setPrettyPrinting().create().toJson(item);
		} else {
			return new Gson().toJson(item);
		}
	}	

	/**
	 * Convert JSON String To Object
	 * 
	 * @param jsonStr
	 * @param inputType
	 * @return
	 */
	public static <T> T jsonToObject(String jsonStr, Class<T> inputType) {
		Gson gson = new Gson();
		return gson.fromJson(jsonStr, inputType);
	}
	
	/**
	 * Convert Object To JSON String
	 * 
	 * @param item
	 * @return
	 */
	public static String toUnderScoreJsonString(Object item) {
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		return gson.toJson(item);
	}

	/**
	 * Convert JSON String To Object
	 * 
	 * @param jsonStr
	 * @param inputType
	 * @return
	 */
	public static <T> T underScoreJsonToObject(String jsonStr, Class<T> inputType) {
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		return gson.fromJson(jsonStr, inputType);
	}
}