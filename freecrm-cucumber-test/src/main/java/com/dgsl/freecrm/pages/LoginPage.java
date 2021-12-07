package com.dgsl.freecrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dgsl.freecrm.base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(xpath = "//*[@placeholder='Username']")
	private static WebElement username;

	@FindBy(xpath = "//*[@placeholder='Password']")
	private static WebElement password;

	@FindBy(xpath = "//*[@value='Login']")
	private static WebElement loginBtn;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public static HomePage login(String un, String pwd){
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		return new HomePage();
	}

}
