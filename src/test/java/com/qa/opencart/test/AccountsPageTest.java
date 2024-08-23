package com.qa.opencart.test;

import org.testng.Assert;
import java.util.List;
import org.testng.annotations.*;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.logger.Log;

public class AccountsPageTest extends BaseTest {

	// List<String> list = Arrays.asList("My Account", "My Orders", "My Affiliate
	// Account", "Newsletter");

	@BeforeClass
	public void accountSetup() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(ap.isLogoutLinkExist(), "Logout is not present in AccountPage");
	}

	@Test
	public void accPageHeadersTest() {
		List<String> accHeadersList = ap.getAccountHeaders();
		Log.info(accHeadersList);
	}

	@Test
	public void searchTest() {
		ap.doSearch("macbook");
	}

}
