package com.test.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.BaseClass.BaseClass;
import com.test.PageObjects.LoginPage;
import com.test.testUtilities.XLUtils;

public class LoginTest_002 extends BaseClass{
	
	@Test(dataProvider = "LoginData")
	public void loginTestData(String user,String pwd) throws IOException {
		driver.get(baseUrl);
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickLoginBtn();
		
		
		
	}
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		String Path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\test\\testData\\LoginData.xlsx";

		int rownum = XLUtils.getRowCount(Path, "Sheet1");
		int colcount = XLUtils.getCellCount(Path, "Sheet1", 1);

		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(Path, "Sheet1", i, j);

			}

		}

		return logindata;

	}

}
