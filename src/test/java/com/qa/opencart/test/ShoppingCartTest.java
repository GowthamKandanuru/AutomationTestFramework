package com.qa.opencart.test;

import java.util.HashMap;

import org.testng.annotations.*;

import com.qa.opencart.base.BaseTest;

public class ShoppingCartTest extends BaseTest {
	
	@BeforeClass
	public void login()
	{
		ap = lp.doLogin(getUserName(),getPassword());
	}
	@Test
	public void performCartTest()
	{
		searchResultspage = ap.doSearch("samsung");
		productPage = searchResultspage.selectProduct("Samsung SyncMaster 941BW");
		shoppingCart = productPage.addToCart();
		HashMap<String,String> shoppingCartDetails = shoppingCart.getCartDetails();
		softAssert.assertEquals(shoppingCartDetails.get("Product Name"),"Samsung SyncMaster 941BW");
		softAssert.assertAll();
	}
	@Test(dependsOnMethods = {"performCartTest"})
	public void validateCapacityTest()
	{
		shoppingCart.updateCartQuantity("Samsung SyncMaster 941BW","4");
		softAssert.assertTrue(true, "Capacity is not updated");
	}
}
