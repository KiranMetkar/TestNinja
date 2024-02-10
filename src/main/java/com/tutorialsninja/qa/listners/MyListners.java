package com.tutorialsninja.qa.listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListners implements ITestListener {
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		extentReport= ExtentReporter.generateExtentReport();	
	}
	@Override
	public void onTestStart(ITestResult result) {	

		extentTest=extentReport.createTest(result.getTestName());
		extentTest.log(Status.INFO,result.getTestName() + "started execution");

	}
	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.log(Status.PASS,result.getTestName() +"got successfully eexecuted");

	}
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Screenshot taken");

		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} 
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {

			e.printStackTrace();
		}
		String destinationScreenshotPath=Utilities.captureScreenshot(driver,result.getName());
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getTestName() +"test failed");
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,result.getTestName() +"got skipped");
	}
	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}