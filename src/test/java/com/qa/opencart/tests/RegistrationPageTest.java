package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class RegistrationPageTest extends BaseTest{

	@BeforeClass
	public void setUpRegistration()
	{
		registerPage = loginPage.goToRegisterPage();
	}
	
//	@DataProvider
//	public Object[][] registerationData()
//	{
//		return ExcelUtil.getTestData("registration");
//	}
	
	@Test
	public void userRegistrationTest()
	{
		Assert.assertTrue(registerPage.doRegister("khushi", "LN1111", "khushiLN1112@gmail.com", 
				"1234567890", "12345","Red and White","100,Lotus Arcade","110,Lotus Arcade","Surat","395006",
				"India","Gujarat","Khushi@123", "yes"));
	}
	
}
