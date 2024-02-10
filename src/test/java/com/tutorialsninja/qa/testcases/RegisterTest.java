package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
//import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base  {
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountsuccessPage;
	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage=new HomePage(driver);
		registerPage=homePage.navigateToRegisterPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		AccountSuccessPage accountSuccessPage=registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telePhone"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(),dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
	}
	@Test(priority = 2)
	public void verifyRegisteringAnAccountWithProvidingAllFields() {
		AccountSuccessPage accountSuccessPage=registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telePhone"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccessPageHeading(),dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success page is not displayed");
	
		
		}
	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress() {
		registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),prop.getProperty("validEmail"), dataProp.getProperty("telePhone"), prop.getProperty("validPassword"));
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message is regarding duplicate email address is not displayed");
     }
	@Test(priority = 4)
	public void verifyRegisteringAnAccountWithoutFillingAnyDetails() {
		
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.displayStatusOfWarningMessage(dataProp.getProperty("privacyPolicyWarning"),dataProp.getProperty("firstnamewarning"),dataProp.getProperty("lastnamewarning"),dataProp.getProperty("emailWarning"),dataProp.getProperty("telephonewarning"),dataProp.getProperty("passwordWarning")),"Password warning not displayed");

	}
}
