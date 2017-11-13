/* Copyright © HatioLab Inc. All rights reserved. */
package com.example.code.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestUtil {

	/**
	 * GET 방식으로 URL 호출하는데 파라미터 parameters로 호출
	 * 
	 * @param url
	 * @param parameters
	 * @return
	 */
	public Object get(String url, Map<String, ?> parameters) {
		return get(url, parameters, null, null);
	}

	public Object get(String url, Map<String, ?> parameters, String userId, String password) {
		RestTemplate restTemplate = this.getRestTemplate(userId, password);

		if (parameters == null || parameters.isEmpty()) {
			return restTemplate.getForObject(url, Object.class);
		} else {
			return restTemplate.getForObject(url, Object.class, parameters);
		}
	}

	/**
	 * POST 방식으로 URL 호출하는데 파라미터 parameters로 호출
	 * 
	 * @param url
	 * @param request
	 * @param parameters
	 * @return
	 */
	public Object post(String url, Object request, Map<String, ?> parameters) {
		return post(url, request, parameters, null, null);
	}

	public Object post(String url, Object request, Map<String, ?> parameters, String userId, String password) {
		RestTemplate restTemplate = this.getRestTemplate(userId, password);

		if (parameters == null || parameters.isEmpty()) {
			return restTemplate.postForObject(url, request, Object.class);
		} else {
			return restTemplate.postForObject(url, request, Object.class, parameters);
		}
	}

	/**
	 * PUT 방식으로 URL 호출하는데 파라미터 parameters로 호출
	 * 
	 * @param url
	 * @param request
	 * @param parameters
	 * @return
	 */
	public void put(String url, Object request, Map<String, ?> parameters) {
		put(url, request, parameters, null, null);
	}

	public void put(String url, Object request, Map<String, ?> parameters, String userId, String password) {
		this.getRestTemplate(userId, password).put(url, request, parameters);
	}

	/**
	 * DELETE 방식으로 URL 호출하는데 파라미터 parameters로 호출
	 * 
	 * @param url
	 * @param request
	 * @param parameters
	 * @return
	 */
	public void delete(String url, Map<String, ?> parameters) {
		delete(url, parameters);
	}

	public void delete(String url, Map<String, ?> parameters, String userId, String password) {
		this.getRestTemplate(userId, password).delete(url, parameters);
	}

	/**
	 * RestTemplate 리턴.
	 * 
	 * @return
	 */
	private RestTemplate getRestTemplate(String userId, String password) {
		if (userId == null || password == null)
			return new RestTemplate();

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory() {
			@Override
			protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
				super.prepareConnection(connection, httpMethod);
				// 인증 정보.
				connection.setRequestProperty("j_username", new String(Base64.encodeBase64(userId.getBytes())));
				connection.setRequestProperty("j_password", new String(Base64.encodeBase64(password.getBytes())));
			}
		};

		return new RestTemplate(requestFactory);
	}
}