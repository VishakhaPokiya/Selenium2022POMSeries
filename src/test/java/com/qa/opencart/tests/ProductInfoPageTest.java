package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void setup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	

	@Test(priority=1)
	public void productHeaderTest() {
		searchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}

	@Test(priority=2)
	public void productImageCountTest() {
		searchResultsPage = accountsPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductImageCount(), Constants.MACPRO_IMAGE_COUNT);
	}
	
	@Test(priority=3)
	public void productInfoTest()
	{
		searchResultsPage = accountsPage.doSearch("Macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoTest = productInfoPage.getProductInfo();
		actProductInfoTest.forEach((k,v) -> System.out.println(k + ":" + v));
		softAssert.assertEquals(actProductInfoTest.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoTest.get("price"), "$2,000.00");
		softAssert.assertEquals(actProductInfoTest.get("Product Code"), "Product 18");
		softAssert.assertAll();
	}
}
