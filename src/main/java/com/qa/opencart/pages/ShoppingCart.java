package com.qa.opencart.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ShoppingCart {
	
	private WebDriver driver;
	HashMap<String,String> shoppingCart = new HashMap<String,String>();
	private ElementUtil eutil;
	private final By productName = By.xpath("//body//div[@class='table-responsive']//tbody/tr/td[2]/a");
	private final By image = By.xpath("//body//div[@class='table-responsive']//tbody/tr/td[1]");
	private final By model = By.xpath("//body//div[@class='table-responsive']//tbody/tr/td[3]");
	private final By unitPrice = By.xpath("//body//div[@class='table-responsive']//tbody/tr/td[5]");
	private final By price = By.xpath("//body//div[@class='table-responsive']//tbody/tr/td[6]");
	private final By quantityTextbox = By.xpath("//input[contains(@name,'quantity')]");
	private final By warningMessage = By.xpath("//body//div[contains(text(),'Success: You have modified your shopping cart!')]");

	public ShoppingCart(WebDriver driver) {
		
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}
	
	public HashMap<String,String> getCartDetails()
	{
		shoppingCart.put("Product Name", eutil.waitForElementVisible(productName, 60).getText());
		shoppingCart.put("Model",eutil.doGetElementText(model));
		shoppingCart.put("Unit Price",eutil.doGetElementText(unitPrice));
		shoppingCart.put("Price",eutil.doGetElementText(price));
		return shoppingCart;
	}
	
	public boolean updateCartQuantity(String product,String quantity)
	{
		eutil.waitForElementVisible(By.xpath("//body//div[@class='table-responsive']//tbody//tr//a[text()='"+product+"']//ancestor::tr/td/div/input"), 60).clear();
		eutil.waitForElementVisible(By.xpath("//body//div[@class='table-responsive']//tbody//tr//a[text()='"+product+"']//ancestor::tr/td/div/input"), 60).sendKeys(quantity);
		eutil.doActionsClick(By.xpath("//body//div[@class='table-responsive']//tbody//tr//a[text()='"+product+"']//ancestor::tr/td/div/span/button[@type='submit']"));
		eutil.waitForElementVisible(warningMessage,60);
		if(!(eutil.isElementExist(warningMessage)))
		{
			return false;
		}else {
			return true;
		}
		
	}
}
