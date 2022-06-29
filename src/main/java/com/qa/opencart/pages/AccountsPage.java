package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleutil;
	
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	private By header = By.xpath("//img[@title='Antropy Demo Store']");
	private By accountSections = By.cssSelector("div#content h2");
	private By searchField = By.xpath("//input[@type='text']");
	private By searchButton = By.cssSelector("div#search button");
	private By logOutLink = By.linkText("Logout");
	
	public String getAccountPageTitle()
	{
		return eleutil.doGetTitle(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccountsPageHeader()
	{
		return eleutil.doGetText(header);
	}
	
	public boolean isLogOutLinkExist()
	{
		return eleutil.doIsDisplayed(logOutLink);
	}
	public void logOut()
	{
		if(isLogOutLinkExist())
		{
			eleutil.doClick(logOutLink);
		}
	}
	public List<String> getAccountSectionList()
	{
		List<WebElement> accountActList = eleutil.waitForElementsToBeVisible(accountSections, 10);
		List<String> accountValueList = new ArrayList<String>();
		for(WebElement e : accountActList)
		{
			String text = e.getText();
			accountValueList.add(text);
		}
		return accountValueList;
	}
	
	public boolean isSearchExist()
	{
		return eleutil.doIsDisplayed(searchButton);
	}
	
	public SearchResultsPage doSearch(String productName)
	{
		System.out.println("Search this : " + productName);
		eleutil.doSendKeys(searchField, productName);
		eleutil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}
}
