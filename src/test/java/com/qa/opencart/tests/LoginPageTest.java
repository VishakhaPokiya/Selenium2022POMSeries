package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100:  Design Open Cart App - Login Page")
@Story("Us 101:Open Cart Login Design with multiple features")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest{

	@Description("login Page Title Test")
	@Severity(SeverityLevel.MINOR)
	
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("Page title is : " + actTitle);
		Assert.assertEquals(actTitle,Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("login Page Url Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageUrlTest()
	{
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	
	@Description("forgot Password Link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3) 
	public void forgotPswdLinkTest()
	{
		Assert.assertTrue(loginPage.isForgotPswdLinkExist());
	}
	
	@Description("register Link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4,enabled = true)
	public void registerLinkTest()
	{
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Description("login Test With Correct Credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	public void loginTest()
	{
		loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
}
