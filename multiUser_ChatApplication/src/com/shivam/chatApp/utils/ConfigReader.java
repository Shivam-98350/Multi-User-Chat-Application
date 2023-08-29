package com.shivam.chatApp.utils;

import java.util.ResourceBundle;

public class ConfigReader {
	public ConfigReader() {} //we created an empty constructor so that no one can create its object
	
	private static ResourceBundle rb = ResourceBundle.getBundle("configuration");
	
	public static String getValue(String key) {
		return rb.getString(key);
	}

}