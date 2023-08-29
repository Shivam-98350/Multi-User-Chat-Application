package com.shivam.chatApp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encryption {
//we want to encrypt/hide the password in mysql
	
	public static String passwordEncrypt(String plainPassword) throws NoSuchAlgorithmException {
		String encryptedPassword = null;
		
		MessageDigest messageDigest = MessageDigest.getInstance("MD5"); //MD5 is the hashing algorithm
		messageDigest.update(plainPassword.getBytes());
		byte[] encrypt = messageDigest.digest();
		StringBuffer sb = new StringBuffer();
		for(byte b : encrypt) {
			sb.append(b);
		}
		encryptedPassword = sb.toString();
		return encryptedPassword;
	}
}
