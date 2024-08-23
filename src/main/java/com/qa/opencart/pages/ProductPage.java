package com.qa.opencart.pages;

import java.util.*;
import org.openqa.selenium.*;
import org.testng.log4testng.Logger;

import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;

public class ProductPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	private Map<String, Object> metaData = new LinkedHashMap<String, Object>();

	private final By productDescriptionTextSingle = By.cssSelector("div#tab-description>div");
	private final By addToCart = By.cssSelector("button#button-cart");
	private final By priceTag = By.cssSelector("div.col-sm-4>ul.list-unstyled>li>h2");
	private final By images = By.cssSelector("ul.thumbnails img");
	private final By productMultipleDesc = By.cssSelector("div#tab-description>p");
	private final By productMetaData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]/li");
	private final By productPriceData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]/li");
	private final By productHeader = By.xpath("//h1");
	private final By shoppingCart = By.xpath("//span[text()='Shopping Cart']");

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	public String getPriceTag() {
		Log.info("Capturing price tag.. in product details page");
		String price = eutil.waitForElementVisible(priceTag, 10).getText();
		Log.info("Captured Product price" + price);
		return price;
	}

	public void getProductDescription() {
		List<String> description = new LinkedList<String>();
		Log.info("Capturing description");

		if (eutil.multipleElementsExist(productMultipleDesc)) {
			description = eutil.getElementsTextList(productMultipleDesc);
			for (String desc : description) {
				Log.info(desc);
			}
		} else {
			Log.info(eutil.doGetElementText(productDescriptionTextSingle));
		}
	}

	public ShoppingCart addToCart() {
		eutil.waitForElementVisible(addToCart, 60).click();
		eutil.waitForElementVisible(shoppingCart,60).click();
		return new ShoppingCart(driver);
	}

	public int getProductImagesCount() {
		int totalImages = eutil.waitForElementsVisible(images, 20).size();
		Log.info("Image count " + totalImages);
		return totalImages;
	}

	public String getProductHeader() {
		return eutil.doGetElementText(productHeader);
	}

	private void getProductMetadata() {
		List<WebElement> list = eutil.getElements(productMetaData);
		for (WebElement e : list) {
			String metaKey = e.getText().split(":")[0].trim();
			String metaValue = e.getText().split(":")[1].trim();
			metaData.put(metaKey, metaValue);
		}
	}

	public Map<String, Object> getProductDetails() {
		metaData.put("Header", getProductHeader());
		getProductMetadata();
		metaData.put("priceTag", getPriceTag());
		metaData.put("Images Count", getProductImagesCount());
		Log.info(metaData);
		return metaData;
	}
}
