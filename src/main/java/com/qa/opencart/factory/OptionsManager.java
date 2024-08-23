package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.logger.Log;

public class OptionsManager {
	
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	 ChromeOptions getChromeOptions(Properties prop)
	{
		co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			Log.info("Running chrome in the headless mode");
			co.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			co.addArguments("--incognito");
		}
		return co;
	}
	
	 FirefoxOptions getFirefoxOptions(Properties prop)
	{
		fo = new FirefoxOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			Log.info("Running firefox in the headless mode");
			co.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			co.addArguments("--incognito");
		}
		return fo;
	}
	
	 EdgeOptions getEdgeOptions(Properties prop)
	{
		eo = new EdgeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
		{
			Log.info("Running edge in the headless mode");
			co.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim()))
		{
			co.addArguments("--incognito");
		}
		return eo;
	}
	

}
