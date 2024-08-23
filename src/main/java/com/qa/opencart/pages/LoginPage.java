package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constansts.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eutil;
	// 1. Private By locators

	// 2. Public Page class Constructors

	// 3. Public Page Actions/Method

	private By emailId = By.xpath("//input[@id='input-email']");
	private By password = By.id("input-password");
	private By loginButton = By.cssSelector("input[type=submit]");
	private By forgotPassword = By.xpath("//a[text()='Forgotten Password'][not(contains(@class,'list-group-item'))]");

	public LoginPage(WebDriver driver) {
		eutil = new ElementUtil(driver);
		this.driver = driver;
	}

	public String getLoginPageTitle() {
		String title = eutil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_Long_TIME);
		return title;
	}

	public String getLoginPageUrl() {
		String url = eutil.waitForURLContains(AppConstants.URL, TimeUtil.DEFAULT_Long_TIME);
		Log.info("Get Current Url : " + url);
		return url;
	}

	public boolean isForgotPwdLinkExist() {
		return eutil.isElementExist(forgotPassword);
	}
	@Step("Login with username {0} and password {1}")
	public AccountPage doLogin(String username, String Password) {
		eutil.doActionsSendKeys(emailId, username);
		eutil.doSendKeys(password, Password);
		eutil.doClick(loginButton);
		return new AccountPage(driver);
	}
}