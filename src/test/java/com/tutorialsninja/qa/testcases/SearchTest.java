package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	public WebDriver driver;
	SearchPage searchpage;
	HomePage homePage;

	public SearchTest() {
		super();
	}
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage=new HomePage(driver);
	}
	@AfterMethod
	public  void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		searchpage=homePage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchpage.displayStatusOfHPProduct(),"Valid product HP is not displayed");
	}
	@Test(priority=2)
	public void verifySearchWithInvalidSearchName() {
		searchpage=homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchpage.retrieveNoProductMessageText(),dataProp.getProperty("noProductSearchInResults"),"No product message in search results is not displayed");
	}
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct() {
		searchpage=homePage.clickOnssearchButton();
		Assert.assertEquals(searchpage.retrieveNoProductMessageText(),dataProp.getProperty("noProductSearchInResults"),"No product message in search results is not displayed");
	}
}