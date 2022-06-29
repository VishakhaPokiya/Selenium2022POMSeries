package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest{

	@DataProvider
	public Object[][] loginWrongTestData()
	{
		return new Object[][] {
			{"test11gmail.com","test123"},
			{"virohi@gmail.com","test@12"},
			{"naveen@gmail.com","test@1234"},
			{"duiycfhikh#@gmail.com","test@12"},
			{" ","  "}
		};			
	}
	
	@Test(dataProvider = "loginWrongTestData")
	public void loginNegativeTest(String userName,String password)
	{
		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(userName, password));
	}
	
}
