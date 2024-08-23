package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eutil;

	public SearchResultsPage(WebDriver driver) {

		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	private final By searchResults = By.xpath("//div[@class='product-thumb']//h4");
	private final By pageCapacity = By.xpath("//select[@id='input-limit']");
	private final String productName = "//a[text()='%s']";

	public int getSearchresultsCount() {
		return eutil.waitForElementsVisible(searchResults, 10).size();
	}

	public List<String> getSearchProductNames() {
		return eutil.getElementsTextList(searchResults);
	}

	public void selectPageCapacity(String count) {
		eutil.doSelectByVisibleText(pageCapacity, count);
	}

	public void navigateTobackPage() {
		eutil.navigateToBack();
	}

	public ProductPage selectProduct(String product) {
		eutil.waitForElementVisible(By.xpath("//img[@title='"+product+"']/parent::a"),180,180).click();
		return new ProductPage(driver);
	}

}
