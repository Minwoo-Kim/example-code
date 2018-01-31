package com.java.custom;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.crypto.Cipher;

public class RSAEncoder {
	private final int READ_SIZE = 512;
	// private final int KEY_PAIR_GENERATOR_SIZE = 4096;
	private final int KEY_PAIR_GENERATOR_SIZE = 1024;

	private final String RSA = "RSA";
	private final String DELIMITER = "$";

	private final String LICENSE_SPEC_PATH = "spec";
	private final String LICENSE_PATH = "license";
	private final String LICENSE_FILE_NAME = "LICENSE";

	private final String COMPILE_PATH = "bin/";
	private final String RESOURCE_PATH = "src/main/resources/";

	private final String PUBLIC_KEY_MODULUS = "PublicKeyModulus";
	private final String PUBLIC_KEY_EXPONENT = "PublicKeyExponent";
	private final String PRIVATE_KEY_MODULUS = "PrivateKeyModulus";
	private final String PRIVATE_KEY_EXPONENT = "PrivateKeyExponent";

	private String licenseSpecPath;
	private String publicKeyModulus;
	private String publicKeyExponent;
	private String privateKeyModulus;
	private String privateKeyExponent;
	private String decryptedText;

	public static void main(String[] args) throws Exception {
		RSAEncoder encoder = new RSAEncoder();
		// 1.Crate License Spec.
		// encoder.generateLicenseSpec();

		// 2.Encode
		String encodeValue = encoder.encrypt("TestKey");
		System.out.println(encodeValue);

		// 3.Decode
		String decodeValue = encoder.decrypt();
		System.out.println(decodeValue);
	}

	/**
	 * Public, Private에 대한 License Spec 생성
	 */
	public void generateLicenseSpec() throws Exception {
		KeyPair keyPair = this.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic(); // 공개키
		PrivateKey privateKey = keyPair.getPrivate(); // 개인키

		/**
		 * Public Spec
		 */
		RSAPublicKeySpec publicKeySpec = this.getRSAPublicKeySpec(publicKey);
		String publicKeyModulus = publicKeySpec.getModulus().toString(16);
		String publicKeyExponent = publicKeySpec.getPublicExponent().toString(16);

		/**
		 * Private Spec
		 */
		RSAPrivateKeySpec privateKeySpec = this.getRSAPrivateKeySpec(privateKey);
		String privateKeyModulus = privateKeySpec.getModulus().toString(16);
		String privateKeyExponent = privateKeySpec.getPrivateExponent().toString(16);

		/**
		 * File 생성
		 */
		String path = this.getResourcePath(LICENSE_SPEC_PATH);

		this.createFile(path, PUBLIC_KEY_MODULUS, publicKeyModulus);
		this.createFile(path, PUBLIC_KEY_EXPONENT, publicKeyExponent);
		this.createFile(path, PRIVATE_KEY_MODULUS, privateKeyModulus);
		this.createFile(path, PRIVATE_KEY_EXPONENT, privateKeyExponent);
	}

	/**
	 * Public Key로 암호화한 후 결과로 출력된 byte 배열을 Base64로 인코딩하여 String으로 변환하여 리턴함
	 * 
	 * @param text
	 * @return Base64로 인코딩된 암호화 문자열
	 */
	public String encrypt(String text) throws Exception {
		String publicKeyModulus = this.getPublicKeyModulus();
		String publicKeyExponent = this.getPublicKeyExponent();

		BigInteger modulus = new BigInteger(publicKeyModulus, 16);
		BigInteger exponent = new BigInteger(publicKeyExponent, 16);

		RSAPublicKeySpec pubks = new RSAPublicKeySpec(modulus, exponent);

		return this.encrypt(text, KeyFactory.getInstance(RSA).generatePublic(pubks));
	}

	private String encrypt(String text, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		int length = text.length();
		int start = 0;
		int end = 0;

		StringBuilder encryptText = new StringBuilder();

		do {
			end = start + READ_SIZE;
			if (end > length) {
				end = length;
			}

			String value = text.substring(start, end);

			encryptText.append(Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes())));
			encryptText.append(DELIMITER);

			start = end;
		} while (end < length);

		// File 생성
		StringBuilder path = new StringBuilder();
		path.append(this.getResourcePath(""));
		path.append(LICENSE_PATH);

		this.createFile(path.toString(), LICENSE_FILE_NAME, encryptText.toString());

		return encryptText.toString();
	}

	/**
	 * decode 시킨 후 RSA 비밀키(Private Key)를 이용하여 암호화된 텍스트를 원문으로 복호화
	 * 
	 * @return
	 */
	public String decrypt() throws Exception {
		return this.decrypt(false);
	}

	public String decrypt(boolean isRefresh) throws Exception {
		if (decryptedText != null && !isRefresh)
			return decryptedText;

		String privateKeyModulus = this.getPrivateKeyModulus();
		String privateKeyExponent = this.getPrivateKeyExponent();

		BigInteger modulus = new BigInteger(privateKeyModulus, 16);
		BigInteger exponent = new BigInteger(privateKeyExponent, 16);

		RSAPrivateKeySpec priks = new RSAPrivateKeySpec(modulus, exponent);

		String dirPath = this.getResourcePath(LICENSE_PATH);
		String encryptedText = this.readFile(dirPath, LICENSE_FILE_NAME);
		return this.decrypt(encryptedText, KeyFactory.getInstance(RSA).generatePrivate(priks));
	}

	private String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
		if (encryptedText == null) {
			throw new RuntimeException("Encrypted Text is Empty.");
		}

		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		StringBuilder sb = new StringBuilder();
		StringTokenizer strs = new StringTokenizer(encryptedText, DELIMITER);

		while (strs.hasMoreTokens()) {
			String token = strs.nextToken();
			byte[] bytes = Base64.getDecoder().decode(token.getBytes());
			sb.append(new String(cipher.doFinal(bytes)));
		}

		decryptedText = sb.toString();
		return decryptedText;
	}

	private KeyPair generateKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
		keyPairGenerator.initialize(KEY_PAIR_GENERATOR_SIZE);
		return keyPairGenerator.genKeyPair();
	}

	/**
	 * RSA 공개키로부터 RSAPublicKeySpec 객체를 생성함
	 * 
	 * @param publicKey
	 *            공개키
	 * @return RSAPublicKeySpec spec
	 */

	private RSAPublicKeySpec getRSAPublicKeySpec(PublicKey publicKey) throws Exception {
		return KeyFactory.getInstance(RSA).getKeySpec(publicKey, RSAPublicKeySpec.class);
	}

	/**
	 * RSA 비밀키로부터 RSAPrivateKeySpec 객체를 생성함
	 * 
	 * @param privateKey
	 *            비밀키
	 * @return RSAPrivateKeySpec
	 */
	private RSAPrivateKeySpec getRSAPrivateKeySpec(PrivateKey privateKey) throws Exception {
		return KeyFactory.getInstance(RSA).getKeySpec(privateKey, RSAPrivateKeySpec.class);
	}

	private String getResourcePath(String resource) {
		// Create Directory.
		String resourcePath = getClass().getClassLoader().getResource("").getPath();
		resourcePath = resourcePath.toString().replace(COMPILE_PATH, RESOURCE_PATH);

		File dir = new File(resourcePath, resource);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// Get Resource Path.
		StringBuilder path = new StringBuilder();
		path.append(getClass().getClassLoader().getResource(resource).getFile());
		return path.toString().replace(COMPILE_PATH, RESOURCE_PATH);
	}

	private String getLicenseSpecPath() throws Exception {
		if (licenseSpecPath == null) {
			licenseSpecPath = this.getResourcePath(LICENSE_SPEC_PATH);
		}
		return licenseSpecPath;
	}

	private String getPublicKeyModulus() throws Exception {
		if (publicKeyModulus == null) {
			publicKeyModulus = this.readFile(this.getLicenseSpecPath(), PUBLIC_KEY_MODULUS);
		}
		return publicKeyModulus;
	}

	private String getPublicKeyExponent() throws Exception {
		if (publicKeyExponent == null) {
			publicKeyExponent = this.readFile(this.getLicenseSpecPath(), PUBLIC_KEY_EXPONENT);
		}
		return publicKeyExponent;
	}

	private String getPrivateKeyModulus() throws Exception {
		if (privateKeyModulus == null) {
			privateKeyModulus = this.readFile(this.getLicenseSpecPath(), PRIVATE_KEY_MODULUS);
		}
		return privateKeyModulus;
	}

	private String getPrivateKeyExponent() throws Exception {
		if (privateKeyExponent == null) {
			privateKeyExponent = this.readFile(this.getLicenseSpecPath(), PRIVATE_KEY_EXPONENT);
		}
		return privateKeyExponent;
	}

	/**************************************************
	 * File Controll
	 **************************************************/

	private void createFile(String dirPath, String fileName, String content) throws Exception {
		// 1. Directory 체크 후 존재하지 않으면 생성
		File dir = new File(dirPath);

		if (!dir.exists()) {
			dir.mkdirs();
		}

		// 2. File 구분자 추가.
		if (!dirPath.endsWith(File.separator)) {
			dirPath += File.separator;
		}

		File file = new File(dirPath, fileName);
		try (FileWriter fileWriter = new FileWriter(file); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
			bufferedWriter.write(content);
		}
	}

	private String readFile(String dirPath, String fileName) throws Exception {
		return readFile(new File(dirPath, fileName));
	}

	private String readFile(File file) throws Exception {
		if (!file.exists())
			return null;

		return new String(Files.readAllBytes(Paths.get(file.toString())), "UTF-8");
	}
}