/**
 * 
 */
package com.example.code.eco.json;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.reflect.FieldUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

/**
 * @author Minu.Kim
 *
 */
public class JsonEx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("all")
	public void parseToJavaList() throws ParseException {
		String content = "[{}]";
		Class<?> clazz = null;

		JSONParser jsonParser = new JSONParser();
		JSONArray jsonArray = (JSONArray) jsonParser.parse(content);
		for (int i = 0; i < jsonArray.size(); i++) {
			String jsonString = jsonArray.get(i).toString();

			Map<String, Object> map = this.jsonToObject(jsonString, HashMap.class);
			Object bean = this.setValue(clazz, map);
		}
	}

	/**
	 * Convert Object To JSON String
	 * 
	 * @param item
	 * @return
	 */
	public static String toJsonString(Object item) {
		Gson gson = new Gson();
		return gson.toJson(item);
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
	 * Map Data를 객체에 Bind
	 * 
	 * @param clazz
	 * @param map
	 * @return
	 */
	public Object setValue(Class<?> clazz, Map<String, Object> map) {
		Object instance = null;
		try {
			instance = clazz.newInstance();

			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();

				Object value = map.get(fieldName);
				if (value == null)
					continue;

				FieldUtils.writeDeclaredField(instance, fieldName, value, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return instance;
	}
}
