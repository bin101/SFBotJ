package de.binary101.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.binary101.core.constants.StaticValues;

public final class CryptManager {
	private final static Logger logger = LogManager.getLogger(CryptManager.class);
	
	private static String TRANSFORMATION = "AES/CBC/NoPadding";
	private static String ALGORITHM = "AES";

	private CryptManager() {
	}

	public static String encode(String toEncode, String stringKey) {
		stringKey = stringKey == null ? StaticValues.CryptKey() : stringKey;
		byte[] plaintext = toEncode.getBytes();
		byte[] ciphertext = null;

		Cipher cipher = null;
		SecretKey key = new SecretKeySpec(stringKey.getBytes(), ALGORITHM);

		IvParameterSpec ivParamSpec = new IvParameterSpec(StaticValues
				.CryptIV().getBytes());

		try {
			cipher = Cipher.getInstance(TRANSFORMATION);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			logger.error(e);
			e.printStackTrace();
		}

		try {
			cipher.init(Cipher.ENCRYPT_MODE, key, ivParamSpec);
			ciphertext = cipher.doFinal(plaintext);
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
			logger.error(e);
			e.printStackTrace();
		} 

		return Base64.getEncoder().encodeToString(ciphertext).replace("+", "-").replace("/", "_");
	}

	public static String decodeResponseString(String data, String key) {
		String response = null;

		if (data.startsWith("e") || data.startsWith("E") || data.length() <= 5) {
			response = data;
		} else {
			response = decode(data.replace("|", "").replace("-", "+").replace("_", "/"), key).replaceAll("[|]+$", "");
		}

		return response;
	}

	private static String decode(String toDecode, String stringKey) {
		stringKey = stringKey == null ? StaticValues.CryptKey() : stringKey;
		String plaintext = null;
		byte[] ciphertext = toDecode.getBytes();

		Cipher cipher = null;
		SecretKey key = new SecretKeySpec(stringKey.getBytes(), ALGORITHM);

		IvParameterSpec ivParamSpec = new IvParameterSpec(StaticValues
				.CryptIV().getBytes());

		try {
			cipher = Cipher.getInstance(TRANSFORMATION);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			logger.error(e);
			e.printStackTrace();
		} 

		try {
			cipher.init(Cipher.DECRYPT_MODE, key, ivParamSpec);

			byte[] decodedValue = Base64.getDecoder().decode(ciphertext);
			byte[] decryptedVal = cipher.doFinal(decodedValue);

			plaintext = new String(decryptedVal);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			logger.error(e);
			e.printStackTrace();
		} 

		return plaintext;
	}
	
	
	public static String getSHA1HashData(String data) {
		String result = "";

		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
			byte[] hash = messageDigest.digest(data.getBytes("UTF-8"));

			for (byte b : hash) {
				result += String.format("%02X", b);
			}

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error(e);
			e.printStackTrace();
		}

		return result.toLowerCase();
	}
	
	public static String getMD5HashData(String data) {
		String result = "";
		
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			byte[] hash = messageDigest.digest(data.getBytes("UTF-8"));

			for (byte b : hash) {
				result += String.format("%02X", b);
			}

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error(e);
			e.printStackTrace();
		} 

		return result.toLowerCase();
	}
}
