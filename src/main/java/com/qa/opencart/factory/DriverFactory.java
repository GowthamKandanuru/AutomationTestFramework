package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.constansts.AppConstants;
import com.qa.opencart.excpetions.BrowserException;
import com.qa.opencart.logger.Log;

public class DriverFactory {

	Properties prop;
	OptionsManager optionsManager;
	WebDriver driver;
	public static String highlight;
	
	static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		Log.info("browser name is: " + browserName);
		highlight = prop.getProperty("highlight");
		
		optionsManager = new OptionsManager();
		switch (browserName.trim().toLowerCase()) {

		case "chrome":
			Log.info("launching chrome browser");
			driver = new ChromeDriver(optionsManager.getChromeOptions(prop));
			tlDriver.set(driver);
			break;
		case "firefox":
			Log.info("launching firefox browser");
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions(prop));
			tlDriver.set(driver);
			break;
		case "edge":
			Log.info("launching edge browser");
			driver = new EdgeDriver(optionsManager.getEdgeOptions(prop));
			tlDriver.set(driver);
			break;
		default:
			Log.error("please pass the correct browser: " + browserName);
			throw new BrowserException("Wrong browser name is passed i.e " + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		//driver.get(AppConstants.URL);
		return getDriver();
	}
	
	public static WebDriver getDriver()
	{
		return tlDriver.get();
	}

	public Properties initProp() {
		
		// envName = qa,dev,stage,prod
		// mvn clean install -Denv="qa"
		prop = new Properties();
		String envName = System.getProperty("env");
		FileInputStream ip = null;
		
		try {
			if (envName == null) {
				Log.info("No env is given...hence running it on QA env...");
				ip = new FileInputStream("./src/test/resource/config/config.qa.properties");
			} else {
				Log.info("Running tests on Env: " + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resource/config/config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resource/config/config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resource/config/config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resource/config/config.uat.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resource/config/config.properties");
					break;
				default:
					Log.info("plz pass the right env name... " + envName);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return prop;
	}
	
	public static String getScreenshot(String methodName)
	{
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";
		Log.info(path);
		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
