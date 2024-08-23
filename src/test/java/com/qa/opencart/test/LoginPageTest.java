package com.qa.opencart.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constansts.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.*;
@Epic("Epic 99: Design Open cart login")
public class LoginPageTest extends BaseTest {

	@Description("Verifying login page Ttile")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = lp.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void getLoginPageURL() {
		Allure.description("Validate Login page Url");
		String url = lp.getLoginPageUrl();
		Assert.assertEquals(url, AppConstants.URL);
	}

	@Description("Verifying login page functionality")
	@Test(dependsOnMethods = "getLoginPageURL",dataProvider = "getLoginDetails")
	@Severity(SeverityLevel.CRITICAL)
	public void doLoginTest(String username,String password) {
		//ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		ap = lp.doLogin(username, password);
		assertEquals(ap.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	@DataProvider
	public Object[][] getLoginDetails()
	{
		return ExcelUtil.getTestData("Login");
	}
}
