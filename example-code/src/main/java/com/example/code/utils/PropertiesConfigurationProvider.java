package com.example.code.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesConfigurationProvider {
	public static Map<String, String> CONFIG_MAP;

	public static void setConfig(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			Properties properties = new Properties();
			properties.load(reader);

			CONFIG_MAP = toMap(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<String, String> toMap(Properties properties) {
		Map<String, String> result = new HashMap<String, String>();
		Enumeration<?> propertyNames = properties.propertyNames();
		while (propertyNames.hasMoreElements()) {
			String name = (String) propertyNames.nextElement();
			String value = properties.getProperty(name);
			result.put(name, value);
		}
		return result;
	}
}