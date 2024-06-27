package com.test.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver){
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	
	@FindBy(name="uid")
	WebElement txtuserName;
	@FindBy (name="password")
	WebElement txtpassword;
	@FindBy (name="btnLogin")
	WebElement Loginbtn;
	
	public void setUserName(String userName) {
		txtuserName.sendKeys(userName);
	}
	public void setPassword(String password) {
		txtpassword.sendKeys(password);
	}
	public void clickLoginBtn() {
		Loginbtn.click();
	}
	

}
