package com.dgsl.freecrm.stepdefinition;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.ITestResult;

import com.dgsl.freecrm.base.TestBase;
import com.dgsl.freecrm.util.TestUtil;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePageTest extends TestBase {

	Logger log = Logger.getLogger(HomePageTest.class);

	public HomePageTest() {
		super();
	}

	@Before
	public void setExtent() {
		TestUtil.setExtentReport();
	}
	
	@Given("^user is on home page$")
	public void user_is_on_home_page() {
	}

	@When("^user verify title of the page$")
	public void user_verify_title_of_the_page() {

	}

	@Then("^user verify logo of the page$")
	public void user_verify_logo_of_the_page()  {
	   
	}

	@After
	public void tearDown(ITestResult result) throws IOException {
		TestUtil.setTestStatus(result);
		TestUtil.endExtentReport();
	}

}
