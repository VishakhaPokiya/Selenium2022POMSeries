package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	
	private ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By fax = By.id("input-fax");
	private By companyName = By.xpath("//input[@id='input-company']");
	private By address1 = By.id("input-address-1");
	private By address2 = By.id("input-address-2");
	private By city = By.id("input-city");
	private By postCode = By.id("input-postcode");
	private By country = By.xpath("//select[@id='input-country']");
	private By region = By.xpath("//select[@id='input-zone']");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subScribeYes = By.xpath("(//label[@class='radio-inline'])[1]/input[@name='newsletter']");
	private By subScribeNo = By.xpath("(//label[@class='radio-inline'])[2]/input[@name='newsletter']");
	private By agreeButton = By.xpath("//input[@name='agree']");
	
	private By continueButton = By.xpath("//input[@type='submit']");
	private By successMessage = By.cssSelector("div#content");
	private By registerLink = By.linkText("Register");
	private By logOutLink = By.linkText("Logout");
	
	
	
	public RegisterPage(WebDriver driver)
	{
		
		eleUtil = new ElementUtil(driver);
	}
	public boolean doRegister(String firstName,String lastName,String email,String telephone,String fax,
							String companyName,String address1,String address2,String city,String postCode,
							String country,String region,String password,String subScribe)
	{
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telePhone, telephone); 
		eleUtil.doSendKeys(this.fax, fax);
		eleUtil.doSelectDropDownValue(this.companyName, companyName);
		eleUtil.doSendKeys(this.address1, address1);
		eleUtil.doSendKeys(this.address2, address2);
		eleUtil.doSendKeys(this.city, city);
		eleUtil.doSendKeys(this.postCode, postCode);
		eleUtil.doDropDownSelectByValue(this.country, country);
		eleUtil.doDropDownSelectByValue(this.region, region);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subScribe.equals("yes"))
		{
			eleUtil.doClick(subScribeYes);
		}
		else
		{
			eleUtil.doClick(subScribeNo);
		}
		
		eleUtil.doClick(agreeButton);
		eleUtil.doClick(continueButton);
		String successMsg = eleUtil.waitForElementToBeVisible(successMessage, 5, 1000).getText();
		if(successMsg.contains(Constants.REGISTER_SUCCESS_MESSAGE))
		{
			eleUtil.doClick(logOutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
	
}
