package com.dgsl.freecrm.stepdefinition;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.ITestResult;

import com.dgsl.freecrm.base.TestBase;
import com.dgsl.freecrm.pages.LoginPage;
import com.dgsl.freecrm.util.TestUtil;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPageTest extends TestBase {

	Logger log = Logger.getLogger(LoginPageTest.class);

	public LoginPageTest() {
		super();
	}

	@Before
	public void setExtent() {
		TestUtil.setExtentReport();
	}

	@Given("^user launch and enter url$")
	public void user_launch_and_enter_url() {
		LoginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@When("^title of the page is FreeCRM$")
	public void title_of_the_page_is_FreeCRM() {

	}

	@Then("^user logs into the application$")
	public void user_logs_into_the_application() {

	}
	
	@After
	public void endExtent(ITestResult result) throws IOException {
		TestUtil.setTestStatus(result);
		TestUtil.endExtentReport();
	}

}
