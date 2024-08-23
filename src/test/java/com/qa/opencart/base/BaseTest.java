package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.logger.Log;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.ShoppingCart;
import com.qa.opencart.utils.ElementUtil;

public class BaseTest {

	private WebDriver driver;
	private DriverFactory df = new DriverFactory();
	protected Properties prop;
	ElementUtil eutil;

	protected LoginPage lp;
	protected AccountPage ap;
	protected SearchResultsPage searchResultspage;
	protected ProductPage productPage;
	protected ShoppingCart shoppingCart;
	protected SoftAssert softAssert;

	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserType) {
		prop = df.initProp();
		if(browserType != null)
		{
			Log.info("Re writing browser property value in the config properties file as per Testng xml parameter");
			prop.setProperty("browser", browserType);
		}
		driver = df.initDriver(prop);
		lp = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	protected String getUserName()
	{
		return prop.getProperty("username");
	}
	
	protected String getPassword()
	{
		return prop.getProperty("password");
	}
	
	 @AfterTest public void tearDown() { driver.quit(); } 
	 

}
