package com.test.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.BaseClass.BaseClass;
import com.test.PageObjects.LoginPage;

public class LoginTest_001 extends BaseClass{
	
	@Test(description="Login Test Case")
	public void loginTest() throws IOException {
		driver.get(baseUrl);
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(userName);
		lp.setPassword(password);
		lp.clickLoginBtn();
		
		if(driver.getTitle().equals(homePageTitle)) {
			Assert.assertTrue(true);
			logger.info("Login Test Passed");
		}else {
			captureScreenShot(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Login Test Failed");
			
		}
	}

}
