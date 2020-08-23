package com.internetbanking.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.internetbanking.pageobject.LoginPage;

public class TC_LoginTest_001 extends BaseClass{
	
	@Test
	public void loginTest() throws IOException
	{
		driver.get(baseURL);
		logger.info("URL is opened ");
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Enter user name ");
		lp.setPassword(password);
		logger.info("Enter user password ");
		
		lp.clickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
		}
		else
		{
			captureScrenshot(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login failed");
		}
	}

}
