package com.example.code.java.basic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

import org.apache.commons.io.FileUtils;


public class HttpCurl {
	public static void main(String[] args) {
		String userName = "admin";
		String password = "elidom";

		String path = "http://repo.hatiolab.com/nexus/service/local/repositories/jar_deployed/content/com/hatiolab/elings-boot/2.1.0.1/elings-boot-2.1.0.1.jar";

		try {
			URL url = new URL(path.toString());
			URLConnection connection = url.openConnection();

			String userPass = userName + ":" + password;
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userPass.getBytes()));

			connection.setRequestProperty("Authorization", basicAuth);
			connection.setRequestProperty("X-Requested-With", "Curl");
			
			File targetFile = new File("/Users/minu/Downloads/sample.jar");
			
			FileUtils.copyURLToFile(url, targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void curlInvoke() {
		String userName = "admin";
		String password = "elidom";

		String path = "http://repo.hatiolab.com/nexus/service/local/repositories/jar_deployed/content/com/hatiolab/elings-boot/2.1.0.1/elings-boot-2.1.0.1.jar";

		try {
			URL url = new URL(path.toString());
			URLConnection connection = url.openConnection();

			String userPass = userName + ":" + password;
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userPass.getBytes()));

			connection.setRequestProperty("Authorization", basicAuth);
			connection.setRequestProperty("X-Requested-With", "Curl");

			InputStream inputStream = connection.getInputStream();

			byte[] buffer = new byte[inputStream.available()];
			inputStream.read(buffer);

			File targetFile = new File("/Users/minu/Downloads/sample.jar");
			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
