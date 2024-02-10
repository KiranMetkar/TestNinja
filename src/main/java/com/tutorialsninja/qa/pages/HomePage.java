package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	//objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText="Login")
	private WebElement loginOption;

	@FindBy(linkText="Register")
	private WebElement registerOption ;

	@FindBy(xpath="//input[@class='form-control input-lg']")
	private WebElement searchBoxField;

	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;

	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}	
	//Actions
	public SearchPage clickOnssearchButton() {
		searchButton.click();
		return new SearchPage(driver);
	}
	public SearchPage searchForAProduct(String productText) {
		searchBoxField.sendKeys(productText);
		searchButton.click();
		return new SearchPage(driver);
	}
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public void enterProducIntossearchBoxField(String productText) {
		searchBoxField.sendKeys(productText);
	}

	public LoginPage navigateToLoginPage() {
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
		
	}
	public RegisterPage selectRegisterOption () {
		registerOption.click();
		return new RegisterPage(driver);
	}
	public RegisterPage navigateToRegisterPage() {
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
		
	}

}
