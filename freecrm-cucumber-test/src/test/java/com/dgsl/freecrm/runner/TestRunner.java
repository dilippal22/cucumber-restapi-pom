package com.dgsl.freecrm.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

	@RunWith(Cucumber.class)
	@CucumberOptions(
			features = "src/main/java/com/dgsl/freecrm/feature",
			glue = {"com/dgsl/freecrm/stepdefinition"},
			plugin = {"pretty","html:test-output/FreeCRMReport.html", "junit:junit_xml/FreeCRM.xml"},
			monochrome = true,
			strict = true,
			dryRun = true,
			tags = {"~@SmokeTest", "~@RegressionTest", "~@End2End"}
			)

public class TestRunner {

}
