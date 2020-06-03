package com.nopcommerce.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nopcommerce.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
		
	@Test
	public void loginTest() throws InterruptedException, IOException
	{
		driver.get(baseURL);
		logger.info("URL is Opened....");
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("username is entered");
		lp.setPassword(password);
		logger.info("password is entered");
		lp.clickLogin();
		logger.info("Login clicked");
		Thread.sleep(5000);
		
		if(driver.getTitle().equals("Dashboard / nopCommerce administration")) 
		{
			Assert.assertTrue(true);
			lp.clickLogout();
			logger.info("Login passed");
		}
		else
		{
			captureScreen(driver,"loginTest" );
			Assert.assertTrue(false);
			logger.info("Login failed");
		}
		
	}

	
	
}


