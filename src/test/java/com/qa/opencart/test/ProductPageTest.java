package com.qa.opencart.test;

import java.util.Map;
import org.testng.annotations.*;
import com.qa.opencart.base.BaseTest;

public class ProductPageTest extends BaseTest {

	@BeforeClass
	public void ProductInfoSetup() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductDetailsdata() {
		return new Object[][] { { "macbook", "MacBook Air", "Apple", "Product 17", "700", "Out Of Stock", "$1,202.00",4},
				{ "samsung", "Samsung SyncMaster 941BW", null , "Product 6", null , "2-3 Days", "$242.00",1} };
	}

	@Test(priority = 1,dataProvider="getProductDetailsdata")
	public void productPageTest(String searchKeyword, String productName, String brand, String productCode, String rewardPoints, String availability, String priceTag, int imagesCount) {
		searchResultspage = ap.doSearch(searchKeyword);
		productPage = searchResultspage.selectProduct(productName);
		productPage.getProductDescription();
		Map<String, Object> productDetailsMap = productPage.getProductDetails();
		softAssert.assertEquals(productDetailsMap.get("Brand"), brand);
		softAssert.assertEquals(productDetailsMap.get("Product Code"), productCode);
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), rewardPoints);
		softAssert.assertEquals(productDetailsMap.get("Availability"), availability);
		softAssert.assertEquals(productDetailsMap.get("priceTag"), priceTag);
		softAssert.assertEquals(productDetailsMap.get("Header"), productName);
		softAssert.assertEquals(productDetailsMap.get("Images Count"), imagesCount);
		softAssert.assertAll();
	}
}
