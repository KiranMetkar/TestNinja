package com.tutorialsninja.qa.testcases;

import org.checkerframework.checker.lock.qual.Holding;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {
	LoginPage loginPage;
	public LoginTest() {
		super();
	}
	public WebDriver driver;

	@BeforeMethod
	public void setup() {

		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		loginPage=homePage.navigateToLoginPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}
	@Test(priority = 1,dataProvider="validCredentilasSupplier")
	public void verifyLoginwithValidCredentials(String email,String password) {

		AccountPage accountPage = loginPage.login(email, password);	
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your Account information  option is not displayyed");

	}
	@DataProvider(name="validCredentilasSupplier")
	public Object[][] supplyTestData() {
		Object [][] data= Utilities.getTestDataFromExcel("Login");
		return data;
	}
	@Test(priority = 2)
	public void verifyLoginwithInValidCredentials() {

		AccountPage accountPage= loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidpassword"));		String actualWarningMessage=loginPage.retriveEmailPasswordNotMatchingText();
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingText().contains(dataProp.getProperty("emailPasswordNoMatching")),"Expected warning message is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginwithInValidEmailAndValidPassword() {
		AccountPage accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(),prop.getProperty("validPassword"));
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingText().contains(dataProp.getProperty("emailPasswordNoMatching")),"Expected warning message is not displayed");

	}
	@Test(priority = 4)
	public void verifyLoginwithValidEmailAndInvalidPassword() {
		AccountPage accountPage=loginPage.login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingText().contains(dataProp.getProperty("emailPasswordNoMatching")),"Expected warning message is not displayed");

	}
	@Test(priority = 5)
	public void verifyLoginwithoutProvidingCredentials() {

		loginPage.clickLoginButton();
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingText().contains(dataProp.getProperty("emailPasswordNoMatching")),"Expected warning message is not displayed");

	}

}	
