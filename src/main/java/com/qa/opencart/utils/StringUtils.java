package com.qa.opencart.utils;

public class StringUtils {
	
	public static String getLocator(String byLocator)
	{
		return byLocator.substring(byLocator.indexOf(" ")+1);
	}

	public static String getRandomEmailId()
	{
		return "testautomation "+System.currentTimeMillis()+"@opencart.com";
	}
}
