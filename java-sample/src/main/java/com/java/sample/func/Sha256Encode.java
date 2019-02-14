package com.java.sample.func;

import java.security.MessageDigest;

public class Sha256Encode {
	public static void main(String[] args) {
		Sha256Encode sha256Encode = new Sha256Encode();

		String v = sha256Encode.encryptSHA256("hatio");
		System.out.println(v);
	}

	public String encryptSHA256(String value) {
		try {
			MessageDigest diget = MessageDigest.getInstance("SHA-256");
			diget.reset();
			// TODO 검증 필요.
			// diget.update("salt".getBytes());

			byte byteData[] = diget.digest(value.getBytes("UTF-8"));

			StringBuilder sb = new StringBuilder();

			for (byte data : byteData)
				sb.append(Integer.toString((data & 0xff) + 0x100, 16).substring(1));

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
