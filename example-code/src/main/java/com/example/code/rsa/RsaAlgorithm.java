package com.example.code.rsa;
//package com.algorithm.rsa;
//
//import java.io.FileOutputStream;
//import java.math.BigInteger;
//import java.security.Key;
//import java.security.KeyFactory;
//import java.security.KeyPair;
//import java.security.KeyPairGenerator;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.spec.RSAPrivateKeySpec;
//import java.security.spec.RSAPublicKeySpec;
//
//import javax.crypto.Cipher;
//
//public class RsaAlgorithm {
//
//	public static void main(String[] args) {
//
//
//	}
//	
//	private static void createKey(String password) throws Exception {
//        // RSA 키 쌍 생성
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(1024);
//        KeyPair keyPair = keyPairGenerator.genKeyPair();
//       
//        //공개키를 파일에 쓴다.
//        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
//        FileOutputStream fos = new FileOutputStream("c:\\publicKey");
//        fos.write(publicKeyBytes);
//        fos.close();
//       
//        // 개인 키를 암호화한 후에 파일에 쓴다.
//        byte[] privateKeyBytes = passwordEncrypt(password.toCharArray(), keyPair.getPrivate().getEncoded());
//        fos = new FileOutputStream("c:\\privateKey");
//        fos.write(publicKeyBytes);
//        fos.close();
//  }
//	
//	
//	
//	public static void one() {
//		try {
//			// 공개키 및 개인키 생성
//			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//			keyPairGenerator.initialize(1024);
//
//			KeyPair keyPair = keyPairGenerator.genKeyPair();
//			Key publicKey = keyPair.getPublic(); // 공개키
//			Key privateKey = keyPair.getPrivate(); // 개인키
//
//			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//
//			/**
//			 * Private
//			 */
//			RSAPrivateKeySpec privateKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
//			
//			String privateKeyModulus = privateKeySpec.getModulus().toString(16);
//			String privateKeyExponent = privateKeySpec.getPrivateExponent().toString(16);
//			
//			BigInteger privateModulus = new BigInteger(privateKeyModulus, 16);
//			BigInteger privateExponent = new BigInteger(privateKeyExponent, 16);
//			
//			RSAPrivateKeySpec priks = new RSAPrivateKeySpec(privateModulus, privateExponent);
//			
//			PrivateKey decodePrivateKey = keyFactory.generatePrivate(priks);
//
//			/**
//			 * public
//			 */
//			RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
//			
//			String publicKeyModulus = publicKeySpec.getModulus().toString(16);
//			String publicKeyExponent = publicKeySpec.getPublicExponent().toString(16);
//
//			BigInteger modulus = new BigInteger(publicKeyModulus, 16);
//			BigInteger exponent = new BigInteger(publicKeyExponent, 16);
//			
//			RSAPublicKeySpec pubks = new RSAPublicKeySpec(modulus, exponent);
//
//			PublicKey decodePublicKey = keyFactory.generatePublic(pubks);
//
//			// 암호화(Encryption) 예제
//			String inputStr = "세이프123"; // "세이프123"을 암호화한다!
//
//			Cipher cipher = Cipher.getInstance("RSA");
//			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//			byte[] arrCipherData = cipher.doFinal(inputStr.getBytes()); // 암호화된 데이터(byte 배열)
//			String strCipher = new String(arrCipherData);
//
//			System.out.println(strCipher); // 암호화 결과물 출력(눈으로 보이기에는 깨질 수 있음)
//
//			// 복호화(Decryption) 예제
//			
//			byte[] arrCipherData2 = strCipher.getBytes(); // 암호화된 데이터(byte 배열)
//			
//			cipher.init(Cipher.DECRYPT_MODE, privateKey);
//			byte[] arrData = cipher.doFinal(arrCipherData2);
//			String strResult = new String(arrData);
//
//			System.out.println(strResult); // 복호화 결과물 출력(다시 "세이프123"이 출력됨)
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void encode(String value) {
//		try {
//			String publicKeyModulus = "ad01a9b03d1308311955959b78d08c560047966027c2d7528f65b120e07337bf7066af5ef1f60c855733c74c125927808a7f4da0ff67259dbdd8eb7686b5a78ea8ff645fdd25c0807286c6dd841328b3879d702a85d8e9581bf956b398da975ae1fa2b56a95dc1bb58407a79466a7a6db86c676322dccfffbb9b489e4607a8b3";
//			String publicKeyExponent = "10001";
//			
//			BigInteger modulus = new BigInteger(publicKeyModulus, 16);
//			BigInteger exponent = new BigInteger(publicKeyExponent, 16);
//			
//			RSAPublicKeySpec pubks = new RSAPublicKeySpec(modulus, exponent);
//			
//			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//			PublicKey publicKey = keyFactory.generatePublic(pubks);
//			
//			
//			Cipher cipher = Cipher.getInstance("RSA");
//			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//			byte[] arrCipherData = cipher.doFinal("ABC".getBytes()); // 암호화된 데이터(byte 배열)
//			String strCipher = new String(arrCipherData);
//			System.out.println(strCipher);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//	public static void decode(String value) {
////		String privateKeyModulus = "89f440ff0c3007e7fb8e584377b150c56be1b6650874cab592e2d8b75e4f619479df2acaf210aea6d4590d1da73c96d50f3a2baa4ba05216f4a181fd518a31a3a8ad677e6c0ff51d9e913a9ef5cb3e0af25bb68c6705b7bee454cea8e82ad13267131d6092b49416e1652fda7484f704b08c846724099f38c6de836b542775ab";
////		String privateKeyExponent = "19153c2c237f2830d9e04bd4189338e86c7121c4c500d74aa466729904a0d25138c786f4578708fa7469aca4d39f849d40a38704f3b336b9cdc8ffe59b49d13477f4b47b6a0d7b3e7fa306c1c9c39f99b639b7c97d6fdcbe6c8331b9e3aa18a86e36cb3859b68710296086a4e4f4aa81ba13c6a5362667f5185ac10a0ce75921";
////		
////		BigInteger privateModulus = new BigInteger(privateKeyModulus, 16);
////		BigInteger privateExponent = new BigInteger(privateKeyExponent, 16);
////		
////		RSAPrivateKeySpec priks = new RSAPrivateKeySpec(privateModulus, privateExponent);
////		
////		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
////		PrivateKey privateKey = keyFactory.generatePrivate(priks);
////		
////		
////		Cipher cipher = Cipher.getInstance("RSA");
////		cipher.init(Cipher.DECRYPT_MODE, privateKey);
////		byte[] arrData = cipher.doFinal(arrCipherData);
////		String strResult = new String(arrData);
////
////		System.out.println(strResult); // 복호화 결과물 출력(다시 "세이프123"이 출력됨)
//	}
//}