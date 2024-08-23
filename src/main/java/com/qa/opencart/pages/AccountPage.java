package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constansts.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil eutil;

	private By logoutLink = By.xpath("//a[text()='Logout' and @class='list-group-item']");
	private By search = By.cssSelector("input[name='search']");
	private By searchIcon = By.xpath("//div[@id='search']/child::span/button");
	private By pageHeaders = By.xpath("//div[@id='content']/h2");

	public AccountPage(WebDriver driver) {

		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	public boolean isLogoutLinkExist() {
		try {
			return eutil.waitForElementVisible(logoutLink, 10).isDisplayed();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getAccountPageTitle() {
		return eutil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, 60);
	}

	public SearchResultsPage doSearch(String searchKey) {
		Log.info("Clearing the search Box");
		clearSearchcontent();
		Log.info("Serching for " + searchKey);
		eutil.doActionsSendKeys(search, searchKey);
		eutil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}

	public List<String> getAccountHeaders() {
		return eutil.getElementsTextList(pageHeaders);
	}
	
	public void clearSearchcontent()
	{
		eutil.doClear(search,90);
	}
}
