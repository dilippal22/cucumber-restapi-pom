package com.dgsl.freecrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.dgsl.freecrm.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//img[@class='logo']")
	private static WebElement homePageLogo;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public static String validateHomePageTitle() {
		return driver.getTitle();
	}

	public static boolean validateHomePageLogo() {
		return homePageLogo.isDisplayed();
	}

}
