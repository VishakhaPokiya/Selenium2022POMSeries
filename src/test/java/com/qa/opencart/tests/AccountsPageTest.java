package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void actPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void actPageTitleTest() {
		String actTitle = accountsPage.getAccountPageTitle();
		System.out.println("Title is : " + actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void actPageHeaderTest() {
		String actHeader = accountsPage.getAccountsPageHeader();
		System.out.println("Header is : " + actHeader);
		Assert.assertEquals(actHeader, Constants.ACCOUNT_PAGE_HEADER,Errors.ACCOUNT_PAGE_HEADER_NOT_FOUNT_ERROR_MSG);
	}

	@Test(priority = 3)
	public void isLogOutExistTest() {
		Assert.assertTrue(accountsPage.isLogOutLinkExist());
	}

	@Test(priority = 4)
	public void accPageSectionsTest() {
		List<String> accAccountSectionList = accountsPage.getAccountSectionList();
		Assert.assertEquals(accAccountSectionList, Constants.accList());
	}

	@DataProvider
	public Object[][] productData()
	{
		return new Object[][] {
			{"Macbook"},
			{"Apple"},
			{"Samsung"},
		};	
	}

	@Test(priority = 5,dataProvider="productData")
	public void searchTest(String productName) {
		searchResultsPage = accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultsPage.getProductsListCount()>0);
	}
	
	@DataProvider
	public Object[][] selectProductData()
	{
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung","Samsung SyncMaster 941BW"},
		};	
	}
	
	@Test(priority=6,dataProvider = "selectProductData")
	public void selectProductTest(String productName,String mainProductName)
	{
		searchResultsPage = accountsPage.doSearch(productName);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(),mainProductName);
	}
}
