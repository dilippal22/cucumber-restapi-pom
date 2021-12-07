package com.dgsl.freecrm.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestUtil {

	public static ExtentReports report;
	public static ExtentTest extentTest;
	private static WebDriver driver;

	public static int PAGE_LOAD_TIMEOUT = 10;
	public static int IMPLCIT_WAIT = 20;

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "\\FailedTestsScreenshots\\" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static void setExtentReport() {
		report = new ExtentReports(System.getProperty("user.dir") + "\\test-output\\ExtentReport.html", true);
		report.addSystemInfo("Host-Name", "Dilip-Windows-local");
		report.addSystemInfo("Username", "Dilip");
		report.addSystemInfo("Environment", "Test");
	}

	public static void endExtentReport() {
		report.flush();
		report.close();
	}

	public static void setTestStatus(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			// To add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName());
			// To add error or exception in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable());

			String screenshotPath = getScreenshot(driver, result.getName());
			// To add screenshot in extent report
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
			extentTest.log(LogStatus.SKIP, "TEST CASE SKIPPED IS " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());
			extentTest.log(LogStatus.PASS, "TEST CASE PASSED IS " + result.getThrowable());

		}

		// ending test and ends the current test and prepare to create html report
		report.endTest(extentTest);
		driver.quit();
	}

}
