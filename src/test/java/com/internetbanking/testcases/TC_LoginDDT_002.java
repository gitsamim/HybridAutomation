package com.internetbanking.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.internetbanking.pageobject.LoginPage;
import com.internetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{

	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException
	{
		System.out.println("samim rows 11: ");
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickSubmit(); 
		
		Thread.sleep(3000);
		
		if(isAlertPresent() == true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login Failed ."); 
		}
		else {
			Assert.assertTrue(true);
			logger.warn("Login passed .");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	//To check alert is present or not
	public boolean isAlertPresent()
	{
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException, EncryptedDocumentException, InvalidFormatException 
	{
		String path = System.getProperty("user.dir")+"/src/test/java/com/internetbanking/testdata/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		System.out.println("samim rows : " + rownum);
		System.out.println("samim column : " + colcount);
		String logindata[][] = new String[rownum][colcount];
		 
		for(int i=1;i<rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
	}
}
