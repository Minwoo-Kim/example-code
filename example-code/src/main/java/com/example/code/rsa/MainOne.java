package com.example.code.rsa;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class MainOne {

	public static void main(String[] args) {
		try {
			createKey("PASS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createKey(String password) throws Exception {
		// RSA 키 쌍 생성
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.genKeyPair();

		// 공개키를 파일에 쓴다.
		byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
		FileOutputStream fos = new FileOutputStream("C:\\ExeJars");
		fos.write(publicKeyBytes);
		fos.close();

		// 개인 키를 암호화한 후에 파일에 쓴다.
		byte[] privateKeyBytes = passwordEncrypt(password.toCharArray(), keyPair.getPrivate().getEncoded());
		fos = new FileOutputStream("c:\\privateKey");
		fos.write(publicKeyBytes);
		fos.close();
	}

	private static byte[] passwordEncrypt(char[] password, byte[] plaintext) throws Exception {
		// salt 생성
		byte[] salt = new byte[9];
		Random random = new Random();
		random.nextBytes(salt);

		// PBE 키와 사이퍼 생성
		PBEKeySpec keySpec = new PBEKeySpec(password);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithSHAAndTwofish-CBC");
		SecretKey key = keyFactory.generateSecret(keySpec);
		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 0);

		Cipher cipher = Cipher.getInstance("PBEWithSHAAndTwofish-CBC");
		cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

		byte[] cipherText = cipher.doFinal(plaintext);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(salt);
		baos.write(cipherText);

		return baos.toByteArray();
	}

	private static void encrypt(String fileInput) throws Exception {
		String publicKeyFileName = "c:\\publicKey";
		// 공개 키가 저장된 파일로부터 keyByte의 바이트 배열을 생성한다.
		FileInputStream fis = new FileInputStream(publicKeyFileName);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int theByte = 0;
		while ((theByte = fis.read()) != -1)
			baos.writeTo(baos);
		fis.close();

		byte[] keyBytes = baos.toByteArray();
		baos.close();

		// 인코딩된 키를 RSA 공개 키의 인스턴스로 바꾼다.
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);

		String fileOutput = fileInput + "_ENCRYPTED_FILENAME_SUFFIX";
		DataOutputStream output = new DataOutputStream(new FileOutputStream(fileOutput));

		// RSA 공개 키를 이용하여 세션 키를 암호화할 사이퍼를 생성한다.
		Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);

		// 세션 키 생성
		KeyGenerator rijndaelKeyGenerator = KeyGenerator.getInstance("Rijndael");
		rijndaelKeyGenerator.init(256);
		Key rijndaelKey = rijndaelKeyGenerator.generateKey();

		// RSA 사이퍼를 이용하여 세션 키를 암호화 하고 파일에 저장한다.
		// 키의 길이, 인코딩된 세션 키 형식이다.
		byte[] encodedKeyBytes = rsaCipher.doFinal(rijndaelKey.getEncoded());
		output.writeInt(encodedKeyBytes.length);
		output.write(encodedKeyBytes);

		// 초기화 벡터
		SecureRandom random = new SecureRandom();
		byte[] iv = new byte[16];
		random.nextBytes(iv);

		// IV를 파일에 쓴다
		output.write(iv);

		// IV와 생성한 세션 키를 이용하여 파일의 내용을 암호화한다.
		IvParameterSpec spec = new IvParameterSpec(iv);
		Cipher symmetricCipher = Cipher.getInstance("Rijndael/CBC/PKCS5Padding");
		symmetricCipher.init(Cipher.ENCRYPT_MODE, rijndaelKey, spec);
		CipherOutputStream cos = new CipherOutputStream(output, symmetricCipher);

		FileInputStream input = new FileInputStream(fileInput);
		theByte = 0;
		while ((theByte = input.read()) != -1)
			cos.write(theByte);

		input.close();
		cos.close();
		return;
	}

//	public void two() {
//
//		try {
//			// 공개키 및 개인키 생성
//			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//			keyPairGenerator.initialize(2048);
//
//			KeyPair keyPair = keyPairGenerator.genKeyPair();
//			Key publicKey = keyPair.getPublic(); // 공개키
//			Key privateKey = keyPair.getPrivate(); // 개인키
//
//			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//			RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
//			RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
//
//			System.out.println("public key modulus(" + publicKeySpec.getModulus() + ") exponent(" + publicKeySpec.getPublicExponent() + ")");
//			System.out.println("private key modulus(" + privateKeySpec.getModulus() + ") exponent(" + privateKeySpec.getPrivateExponent() + ")");
//
//			// 암호화(Encryption) 예제
//			String inputStr = "세이프123"; // "세이프123"을 암호화한다!
//
//			Cipher cipher = Cipher.getInstance("RSA");
//			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//			byte[] arrCipherData = cipher.doFinal(inputStr.getBytes()); // 암호화된 데이터(byte 배열)
//			String strCipher = new String(arrCipherData, "UTF-8");
//
//			System.out.println(strCipher); // 암호화 결과물 출력(눈으로 보이기에는 깨질 수 있음)
//
//			// 복호화(Decryption) 예제
//			cipher.init(Cipher.DECRYPT_MODE, privateKey);
//			byte[] arrData = cipher.doFinal(arrCipherData);
//			String strResult = new String(arrData);
//
//			System.out.println(strResult); // 복호화 결과물 출력(다시 "세이프123"이 출력됨)
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}