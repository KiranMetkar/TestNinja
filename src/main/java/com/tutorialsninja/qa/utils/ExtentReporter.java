package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport()  {
		ExtentReports extentReporter=new ExtentReports();
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorials Ninja Test Automation Results");
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReporter.attachReporter(sparkReporter);

		Properties configprop=new Properties();
		File confPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		try {
			FileInputStream fisconfigProp=new FileInputStream(confPropFile);
			configprop.load(fisconfigProp);
		} catch (Throwable e) {

			e.printStackTrace();
		}

		extentReporter.setSystemInfo("Application URL",configprop.getProperty("url") );
		extentReporter.setSystemInfo("Browser Name",configprop.getProperty("browserName"));
		extentReporter.setSystemInfo("Email ",configprop.getProperty("validEmail"));
		extentReporter.setSystemInfo("Password",configprop.getProperty("validPassword"));
		extentReporter.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReporter.setSystemInfo("User Name",System.getProperty("user.name"));
		extentReporter.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		return extentReporter;

		}

}
