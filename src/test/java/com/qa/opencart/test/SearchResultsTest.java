package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.logger.Log;

public class SearchResultsTest extends BaseTest {

	@BeforeClass
	public void searchResultsSetup() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductCount()
	{
		return new Object[][] {
			{"samsung",2},{"macbook",3},{"imac",2}
		};
	}
	
	@Test(priority = 1,dataProvider = "getProductCount")
	public void searchResultsTest(String searchKey, int productCount) {
		searchResultspage = ap.doSearch(searchKey);
		Assert.assertEquals(searchResultspage.getSearchresultsCount(), productCount,searchKey+" results count mismatch in the Cart website");
	}

	@Test(dependsOnMethods = { "searchResultsTest" })
	public void searchResultsTextTest() {
		Log.info(searchResultspage.getSearchProductNames());
	}

	@Test(dependsOnMethods = { "searchResultsTest" })
	public void searchPageCapacityTest() {
		searchResultspage.selectPageCapacity("75");
	}

}
