package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage{

	//1. declare private webdriver
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2. page constructor
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. By locators;
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By registerLink = By.linkText("Register");
	private By forgotpswdLink = By.linkText("Forgotten Password");
	private By errorMessage = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
	
	//4. Page Actions
	@Step("getting login page title value...")
	public String getLoginPageTitle()
	{
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("getting login page url...")
	public boolean getLoginPageUrl()
	{
		return eleUtil.waitForUrlToContains(Constants.LOGIN_PAGE_URL_FRACTION,Constants.DEFAULT_TIME_OUT);
	}
	
	@Step("checking forgot password link exist or not...")
	public boolean isForgotPswdLinkExist()
	{
		return eleUtil.doIsDisplayed(forgotpswdLink);
	}
	
	@Step("checking forgot register link exist or not...")
	public boolean isRegisterLinkExist()
	{
		return eleUtil.doIsDisplayed(registerLink);
	}
	
//	public AccountsPage doLogin(String un,String pwd)
//	{
//		System.out.println("Login with : " + un + " : " + pwd);
//		
//		eleUtil.doSendKeys(emailId, un);
//		eleUtil.doSendKeys(password, pwd);
//		eleUtil.doClick(loginBtn);
//		return new AccountsPage(driver);
//	}
	
	@Step("do login with username : {0} and password : {1}")
	public AccountsPage doLogin(String un,String pwd)
	{
		System.out.println("Login with : " + un + " : " + pwd);
		
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("do login with wrong username : {0} and wrong password : {1}")
	public boolean doLoginWithWrongCredentials(String un,String pwd)
	{
		System.out.println("Try to Login with wrong Credentials: " + un + " : " + pwd);
		
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		String errorMsg = eleUtil.doGetText(errorMessage);
		System.out.println(errorMsg);
		if(errorMsg.contains(Constants.LOGIN_ERROR_MESSAGE))
		{
			System.out.println("Login is not done...");
			return false;
		}
		return true;
	}
	
	@Step("navigating to registration page...")
	public RegisterPage goToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}
