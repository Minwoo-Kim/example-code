package com.example.code.spring.propery;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Service;

@Service
public class AddProperty {
	@Autowired
	ConfigurableEnvironment configurableEnvironment;

	public String readConfigProperty() {
		String value = null;
		String charSet = "UTF-8";
		String propertyFileName = "sample.config";

		Properties properties = new Properties();

		try {
			// Jar 위치에 있는 설정 파일을 Load
			URI uri = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
			File file = new File(uri.getPath(), propertyFileName);
			value = new String(Files.readAllBytes(Paths.get(file.toString())), charSet);

			// Custom 설정 파일을 Property Resource에 등록.
			BufferedReader reader = new BufferedReader(new FileReader(file));
			properties.load(reader);
		} catch (Exception e) {
			// Jar 안에 있는 설정 파일을 Load 후, Jar가 위치하고 있는 곳에 Property File 생성.
			try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName)) {
				value = IOUtils.toString(inputStream, charSet);

				URI uri = this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI();
				File file = new File(uri.getPath(), propertyFileName);

				try (FileWriter fileWriter = new FileWriter(file); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
					bufferedWriter.write(value);
				}

				BufferedReader reader = new BufferedReader(new FileReader(file));
				properties.load(reader);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		// Evironment에 custom propery 추가.
		MutablePropertySources sources = configurableEnvironment.getPropertySources();
		sources.addFirst(new PropertiesPropertySource(propertyFileName, properties));

		return value;
	}
}
