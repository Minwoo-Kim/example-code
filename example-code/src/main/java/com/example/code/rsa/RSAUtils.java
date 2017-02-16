package com.example.code.rsa;
//
//package com.algorithm.rsa;
//
//import java.security.InvalidKeyException;
//import java.security.KeyFactory;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.NoSuchAlgorithmException;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.RSAPrivateKeySpec;
//import java.security.spec.RSAPublicKeySpec;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//
//public class RSAUtils {
//
//	public static final String RSA = "RSA";
//
//	public static KeyPair generateKeyPair() {
//
//		KeyPair keyPair = null;
//
//		try {
//
//			KeyPairGenerator clsKeyPairGenerator = KeyPairGenerator.getInstance(RSA);
//
//			clsKeyPairGenerator.initialize(2048);
//
//			keyPair = clsKeyPairGenerator.genKeyPair();
//
//		} catch (NoSuchAlgorithmException e) {
//
//			e.printStackTrace();
//
//		}
//
//		return keyPair;
//
//	}
//
//	/**
//	 * 
//	 * Public Key로 암호화한 후 결과로 출력된 byte 배열을 Base64로 인코딩하여 String으로 변환하여 리턴함
//	 * 
//	 * @param text
//	 *            암호화할 텍스트
//	 * 
//	 * @param publicKey
//	 *            RSA 공개키
//	 * 
//	 * @return Base64로 인코딩된 암호화 문자열
//	 * 
//	 */
//
//	public static String encrypt(String text, PublicKey publicKey) {
//
//		byte[] bytes = text.getBytes();
//
//		String encryptedText = null;
//
//		try {
//
//			Cipher cipher = Cipher.getInstance(RSA);
//
//			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//
//			encryptedText = new String(Base64.encodeToString(cipher.doFinal(bytes), Base64.DEFAULT));
//
//		}
//
//		catch (NoSuchAlgorithmException e) {
//
//			e.printStackTrace();
//
//		}
//
//		catch (NoSuchPaddingException e) {
//
//			e.printStackTrace();
//
//		}
//
//		catch (InvalidKeyException e) {
//
//			e.printStackTrace();
//
//		}
//
//		catch (IllegalBlockSizeException e) {
//
//			e.printStackTrace();
//
//		}
//
//		catch (BadPaddingException e) {
//
//			e.printStackTrace();
//
//		}
//
//		return encryptedText;
//
//	}
//
//	/**
//	 * 
//	 * decode 시킨 후 RSA 비밀키(Private Key)를 이용하여 암호화된 텍스트를 원문으로 복호화
//	 * 
//	 */
//
//	public static String decrypt(String encryptedText, PrivateKey privateKey) {
//
//		byte[] bytes = Base64.decode(encryptedText.getBytes());
//
//		String decryptedText = null;
//
//		try {
//
//			Cipher cipher = Cipher.getInstance(RSA);
//
//			cipher.init(Cipher.DECRYPT_MODE, privateKey);
//
//			decryptedText = new String(cipher.doFinal(bytes));
//
//		}
//
//		catch (NoSuchAlgorithmException e) {
//
//			e.printStackTrace();
//
//		}
//
//		catch (NoSuchPaddingException e) {
//
//			e.printStackTrace();
//
//		}
//
//		catch (InvalidKeyException e) {
//
//			e.printStackTrace();
//
//		}
//
//		catch (IllegalBlockSizeException e) {
//
//			e.printStackTrace();
//
//		}
//
//		catch (BadPaddingException e) {
//
//			e.printStackTrace();
//
//		}
//
//		return decryptedText;
//
//	}
//
//	/**
//	 * 
//	 * RSA 공개키로부터 RSAPublicKeySpec 객체를 생성함
//	 * 
//	 * @param publicKey
//	 *            공개키
//	 * 
//	 * @return RSAPublicKeySpec spec
//	 * 
//	 */
//
//	public static RSAPublicKeySpec getRSAPublicKeySpec(PublicKey publicKey) {
//
//		RSAPublicKeySpec spec = null;
//
//		try {
//
//			spec = KeyFactory.getInstance(RSA).getKeySpec(publicKey, RSAPublicKeySpec.class);
//
//		}
//
//		catch (InvalidKeySpecException e) {
//
//			e.printStackTrace();
//
//		}
//
//		catch (NoSuchAlgorithmException e) {
//
//			e.printStackTrace();
//
//		}
//
//		return spec;
//
//	}
//
//	/**
//	 * 
//	 * RSA 비밀키로부터 RSAPrivateKeySpec 객체를 생성함
//	 * 
//	 * @param privateKey
//	 *            비밀키
//	 * 
//	 * @return RSAPrivateKeySpec
//	 * 
//	 */
//
//	public static RSAPrivateKeySpec getRSAPrivateKeySpec(PrivateKey privateKey) {
//
//		RSAPrivateKeySpec spec = null;
//
//		try {
//
//			spec = KeyFactory.getInstance(RSA).getKeySpec(privateKey, RSAPrivateKeySpec.class);
//
//		}
//
//		catch (InvalidKeySpecException e) {
//
//			e.printStackTrace();
//
//		}
//
//		catch (NoSuchAlgorithmException e) {
//
//			e.printStackTrace();
//
//		}
//
//		return spec;
//
//	}
//
//	/**
//	 * 
//	 * keyPair 값을 이용하여 PublicKey 객체를 생성함
//	 * 
//	 */
//
//	public static PublicKey getPublicKey(KeyPair keyPair) {
//
//		PublicKey publicKey = null;
//
//		try {
//
//			publicKey = keyPair.getPublic();
//
//		}
//
//		catch (Exception e) {
//
//			e.printStackTrace();
//
//		}
//
//		return publicKey;
//
//	}
//
//	/**
//	 * 
//	 * keyPair 값을 이용하여 PrivateKey 객체를 생성함
//	 * 
//	 */
//
//	public static PrivateKey getPrivateKey(KeyPair keyPair) {
//
//		PrivateKey privateKey = null;
//
//		try {
//
//			privateKey = keyPair.getPrivate();
//
//		}
//
//		catch (Exception e) {
//
//			e.printStackTrace();
//
//		}
//
//		return privateKey;
//
//	}
//
//}