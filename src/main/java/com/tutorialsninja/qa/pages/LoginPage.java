package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	WebDriver driver;

	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailAddressField;

	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordField;

	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;

	public LoginPage(WebDriver driver) {
		this.driver=driver;

	}
	public void enterEmail(String emailText) {
		emailAddressField.sendKeys(emailText);
		
	}
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
		
	}
	public String retriveEmailPasswordNotMatchingText() {
		String warningText=emailPasswordNotMatchingWarning.getText();
		return warningText;
	}
	public AccountPage clickLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
		
	}
	public AccountPage login(String emailText,String PasswordText) {
		emailAddressField.sendKeys(emailText);
		passwordField.sendKeys(PasswordText);
		loginButton.click();
		return new AccountPage(driver);


	}

}
