package com.test.BaseClass;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.test.testUtilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig= new ReadConfig();
	
	public String baseUrl=readconfig.getPropertyValue("baseURL");
	public String userName=readconfig.getPropertyValue("username");
	public String password=readconfig.getPropertyValue("password");
	public String homePageTitle="Guru99 Bank Manager HomePage";
	
	public static Logger logger;
	
	public static WebDriver driver;
	
	@Parameters("Browser")
	@BeforeClass
	public void initializeBrowser(String br) {
		
		logger=Logger.getLogger("e-banking");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome")){
			System.setProperty("webdriver.chrome.driver",readconfig.getPropertyValue("chromepath"));
			//System.getProperty("user.dir")+"\\drivers\\msedgedriver.exe"
			driver=new ChromeDriver();
		}else if(br.equals("edge")){
			System.setProperty("webdriver.chrome.driver",readconfig.getPropertyValue("msedgepath"));
			driver=new EdgeDriver();	
		}
		
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
	//capture screenshot if test case fails
	public void captureScreenShot(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"/screenShots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Taken");
		
	}

}
