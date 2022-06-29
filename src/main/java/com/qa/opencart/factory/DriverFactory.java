package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highLight;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the webdriver
	 * 
	 * @param browserName
	 * @return this will return the driver
	 */
	public WebDriver init_driver(Properties prop) {
		highLight = prop.getProperty("highLight");
		optionsManager = new OptionsManager(prop);

		String browserName = prop.getProperty("browser").trim();

		System.out.println("Browser name is : " + browserName);

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver(optionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		} else {
			System.out.println("Please pass the right browser...");
		}
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
//		openUrl(prop.getProperty("url"));
		URL url;
		try {
			url = new URL(prop.getProperty("url"));
			openUrl(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return getDriver();
	}

	/**
	 * getdriver(): This will return the a thread local copy of driver
	 */

	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * This method is used to initialize the properties This will return properties
	 * prop reference
	 * 
	 * @return
	 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		String env = System.getProperty("env");// qa/dev/uat/stage

		if (env == null) {
			System.out.println("Running on Production environment...");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		} 
		else {
			System.out.println("Running on environment : " + env);
			try {
			switch(env.toLowerCase())
			{
			case "qa" : 
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev" : 
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage" : 
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "uat" : 
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			default:
				System.out.println("please pass the right environment...");
				break;
			}
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * take screenshot
	 */
	
//	public String getScreenshot()
//	{
//		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
//		String path = System.getProperty("user.dir")+"/screenshots"+System.currentTimeMillis()+".png";
//		File destination = new File(path);
//		
//		try {
//			FileUtils.copyFile(src, destination);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return path;
//	}
	
	public String getScreenshot()
	{
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * url 
	 * @param url
	 */
	public void openUrl(String url)
	{
		try {
		if(url==null){
			throw new Exception("url is null");
			}
		}
		catch(Exception e){
			
		}
		getDriver().get(url);
	}
	
	public void openUrl(URL url)
	{
		try {
		if(url==null){
			throw new Exception("url is null");
			}
		}
		catch(Exception e){
			
		}
		getDriver().navigate().to(url);
	}
	public void openUrl(String baseUrl,String path)
	{
		try {
		if(baseUrl==null){
			throw new Exception("baseurl is null");
			}
		}
		catch(Exception e){
			
		}
		//https://amazon.com/accpage/users.html
		getDriver().get(baseUrl+"/"+path);
	}
	
	public void openUrl(String baseUrl,String path,String queryparam)
	{
		try {
		if(baseUrl==null){
			throw new Exception("baseurl is null");
			}
		}
		catch(Exception e){
			
		}
		//https://amazon.com/accpage/users.html?route=account/login
		getDriver().get(baseUrl+"/"+path+"?"+queryparam);
	}
}
