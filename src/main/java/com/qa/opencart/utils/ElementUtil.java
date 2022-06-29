package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public By getBy(String locatorType, String locatorValue) {
		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "cssselector":
			locator = By.cssSelector(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		default:
			System.out.println("Enter the right locator..");
			break;
		}
		return locator;
	}

	public WebElement getElement(By locator) {
		
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highLight))
		{
			jsUtil.flash(element);
		}
		return element;
	}
	
	public WebElement getElement(By locator,int timeOut) {
		return doPresenceofElementLocated(locator, timeOut);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public WebElement getElement(String locatorType, String locatorvalue) {
		return driver.findElement(getBy(locatorType, locatorvalue));
	}

	public List<String> getElementTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();

		for (WebElement e : eleList) {
			String eleText = e.getText();
			if (!eleText.isEmpty()) {
				eleTextList.add(eleText);
			}
		}
		return eleTextList;
	}

	public List<String> getAttributeList(By locator, String attributeName) {

		List<WebElement> eleList = getElements(locator);
		List<String> attrList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attriValue = e.getAttribute(attributeName);
			if (!attriValue.isEmpty()) {
				attrList.add(attriValue);
			}
		}
		return attrList;
	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public String getAttributeValue(By locator, String attributeName) {
		String attrValue = getElement(locator).getAttribute(attributeName);
		System.out.println(attrValue);
		return attrValue;
	}

	public void doClear(By locator)
	{
		getElement(locator).clear();
	}
	
	public void doSendKeys(By locator, String value) {
		doClear(locator);
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorvalue, String value) {
		getElement(locatorType, locatorvalue).sendKeys(value);
	}
	
	public void doSendKeys(By locator,String value,int timeOut)
	{
		doPresenceofElementLocated(locator, timeOut).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(String locatorType, String locatorvalue) {
		getElement(locatorType, locatorvalue).click();
	}
	
	public void doClick(By locator,int timeOut) {
		doPresenceofElementLocated(locator, timeOut).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean isElementExist(By locator) {
		int eleCount = driver.findElements(locator).size();
		System.out.println("Total element found : " + eleCount);
		if (eleCount >= 1) {
			System.out.println("element is found : " + locator);
			return true;
		} else {
			System.out.println("element is not found : " + locator);
			return false;
		}
	}

	/********************************** * Drop Down Utils***********************************************/

	public void doDropDownSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(5);
	}

	public void doDropDownSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doDropDownSelectByValue(By locator, String Value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(Value);
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));

		List<WebElement> countryOptions = select.getOptions();
		for (WebElement e : countryOptions) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void selectDropDownValueWithoutSelectClass(By locator, String value) {
		List<WebElement> countryList = getElements(locator);

		System.out.println(countryList);
		for (WebElement e : countryList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
			}
		}
	}

	/******************************************* Links Utils *************************************************/

	public List<String> getLinksTextList(By locator) {
		List<WebElement> linksList = driver.findElements(By.xpath("//div[@id='SIvCob']//a"));
		List<String> linksTextList = new ArrayList<String>();

		for (WebElement e : linksList) {
			String text = e.getText();
			linksTextList.add(text);
		}
		return linksTextList;
	}

	public void clickOnElementFromSection(By locator,String value)
	{
		List<WebElement> langList = driver.findElements(By.xpath("//div[@id='SIvCob']//a"));
		System.out.println(langList.size());
		
		for(WebElement e : langList)
		{
			String text = e.getText();
			System.out.println(text);
			if(text.equals(value))
			{
				e.click();
				break;
			}
		}
	}
	/*************************************WebTable Util*******************************************************/

	public void printTable(By rowLocator,By columnLocator,String beforeXpath,String afterXpath)
	{
		int rowCount = getElements(rowLocator).size();
		int columnCount = getElements(columnLocator).size();
		
		for(int row = 2; row<=rowCount ; row++)
		{
			for(int column = 1 ; column<=columnCount ; column++)
			{
				String xpath = beforeXpath + row + afterXpath + column + "]";
				String text = doGetText(By.xpath(xpath));
				System.out.print(text + "     |   ");
			}
			System.out.println();
		}
	}
	
	/****************************************Actions Util**************************************************/

	public void doMoveToElement(By locator)
	{
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).build().perform();
	}
	public void doClickOnChildMenyu(By parentMenuLocator,By childMenuLocator) throws InterruptedException
	{
		doMoveToElement(parentMenuLocator);
		Thread.sleep(3000);
		doClick(childMenuLocator);
	}
	public void doActionsSendKeys(By locator , String value)
	{
		Actions action = new Actions(driver);
		action.sendKeys(getElement(locator), value).build().perform();
	}
	public void doActionsSendKeysOnActiveEle(By locator,String value)
	{
		Actions action = new Actions(driver);
		action.click(getElement(locator)).sendKeys(value).build().perform();
	}
	
	/*
	 * Clicks in the middle of the given element. 
	 * Equivalent to: Actions.moveToElement(onElement).click()
	 */

	public void doActionsClick(By locator)
	{
		Actions action = new Actions(driver);
		action.click(getElement(locator)).build().perform();
	}
	public void doActionsMoveToElementCLick(By locator)
	{
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).click().build().perform();
	}
	
	/************************************Wait Utils********************************************************/
	
	
	public WebElement doPresenceofElementLocated(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForElementToBeVisibleWithWebElement(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	} 
	
	public WebElement doPresenceofElementLocated(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public WebElement waitForElementToBeVisible(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public List<WebElement> waitForElementsToBeVisible(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public List<WebElement> waitForElementsToBeVisible(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	public List<String> getElementTextListWithWait(By locator,int timeOut)
	{
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList)
		{
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}
	
	/******************************Wait Utils for Non WebElements*******************************************/
	public boolean waitForUrlToContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}

	public boolean waitForUrlToBe(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlToBe(urlFraction));
	}
	
	public String doGetTitle(String title,int tileOut)
	{
		if(waitForTitleToBe(title, tileOut))
		{
			return driver.getTitle();
		}
		return null;
	}
	
	public String doGetTitleWithFraction(String titleFraction, int timeOut) 
	{
		if (waitForTitleContains(titleFraction, timeOut))
		{
			return driver.getTitle();
		}
		return null;
	}

	public boolean waitForTitleContains(String titleFrcation, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleFrcation));
	}
	
	public boolean waitForTitleToBe(String titleFrcation, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(titleFrcation));
	}

	public Alert waitForAlert(int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	public String getAlertText(int timeOut)
	{
		return waitForAlert(timeOut).getText();
	}
	public void doAlertAccept(int timeOut)
	{
		waitForAlert(timeOut).accept();
	}
	public void doAlertDismiss(int timeOut)
	{
		waitForAlert(timeOut).dismiss();
	}
	public void enterAlertText(String value,int timeOut)
	{
		waitForAlert(timeOut).sendKeys(value);
	}
	
	/**********************************Wait Utils for frame*************************************************/
	public void waitForFrameByName(String nameOrId , int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
	}
	public void waitForFrameByIndex(int frameIndex , int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	public void waitForFrameByLocator(By frameLocator, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}
	public void waitForFrameByElement(By frameElement, int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	public void ClickElementWhenReady(By locator,int timeOut)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public void ClickElementWhenReady(By locator,int timeOut,long intervalTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	public WebElement waitForElementPresentUsingFluentWait(By locator, int timeOut, long pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.withMessage("Element is not found...");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	public WebElement waitForElementPresentUsingWebDriverWait(By locator,int timeOut,long pollingTime)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait
			.withTimeout(Duration.ofSeconds(timeOut))
			.pollingEvery(Duration.ofSeconds(pollingTime))
			.ignoring(NoSuchElementException.class)
			.ignoring(StaleElementReferenceException.class)
			.withMessage(Errors.ELEMNET_NOT_FOUND_ERROR_MESSG);
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));																		
	}
	
	/******************************************Custom Wait****************************************/
	
	public WebElement retryingElement(By locator,int timeOut)
	{
		WebElement element = null;
		int attempts = 0;
		
		while(attempts<timeOut)
		{
			try
			{
				element = getElement(locator);
				break;
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Element is not found : " + attempts + " : " + locator);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
			
		}
		if(element == null)
		{
			try {
				throw new Exception();
			}
			catch(Exception e )
			{
				System.out.println("Element is not found Exception...tried for :" + timeOut +
									" with the interval of :" + 500 + "ms");
			}
		}
		return element;
	}
	public WebElement retryingElement(By locator,int timeOut,long pollingTime)
	{
		WebElement element = null;
		int attempts = 0;
		
		while(attempts<timeOut)
		{
			try
			{
				element = getElement(locator);
				break;
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Element is not found : " + attempts + " : " + locator);
				try {
					Thread.sleep(pollingTime);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			attempts++;
			
		}
		if(element == null)
		{
			try {
				throw new Exception();
			}
			catch(Exception e )
			{
				System.out.println("Element is not found Exception...tried for :" + timeOut +
									" with the interval of :" + pollingTime + "ms");
			}
		}
		return element;
	}
}
