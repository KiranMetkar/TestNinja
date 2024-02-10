package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Factory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id="input-firstname")
	private WebElement firstNameField;

	@FindBy(id="input-lastname")
	private WebElement lastNameField;

	@FindBy(id="input-email")
	private WebElement emailField;

	@FindBy(id="input-telephone")
	private WebElement telephoneField;

	@FindBy(id="input-password")
	private WebElement passwordField;

	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;

	@FindBy(name="agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath="input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath="//div[text()='Warning: E-Mail Address is already registered!']")
	private WebElement duplicateEmailAddressWarning;

	@FindBy(xpath="//div[text()='Warning: You must agree to the Privacy Policy!']")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;

	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public String retrievePasswordWarning() {
		String passwordWarningText=passwordWarning.getText();
		return passwordWarningText;
	}
	public String retrieveTelePhoneWarning() {
		String telephoneWarningText=telephoneWarning.getText();
		return telephoneWarningText;
	}
	public String retrieveEmailNameWarning() {
		String emailWarningText=emailWarning.getText();
		return emailWarningText;
	}

	public String retrieveLastNameWarning() {
		String lastNameWarningText=lastNameWarning.getText();
		return lastNameWarningText;
	}

	public String retrieveFirstNameWarning() {
		String firstNameWarningText=firstNameWarning.getText();
		return firstNameWarningText;
	}

	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarningText=privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}
	public void enterLastName(String lastNameText ) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmailAddess(String emailText) {
		emailField.sendKeys(emailText);
	}
	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}
	public void enterConfirmPassword(String passwordText) {
		passwordConfirmField.sendKeys(passwordText);

	}
	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}
	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	public String retrieveDuplicateEmailAddressWarning(){
		String duplicateEmailWarningTetx=duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningTetx;
	}
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText,String emailText,String telephoneText,String passwordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	
	}
	public boolean displayStatusOfWarningMessage(String expectedprivacyPolicyWarning,String expectedfirstNameWarning,String expectedlastNameWarning,String expectedemailWarning,String expectedtelephoneWarning,String expectedpasswordWarning) {
		
		boolean privacyPolicyWarningStatus=privacyPolicyWarning.getText().contains(expectedpasswordWarning);
		boolean firstNameWarningStatus=firstNameWarning.getText().equals(expectedfirstNameWarning);
		boolean lastNameWarningStatus=lastNameWarning.getText().equals(expectedlastNameWarning);
		boolean emailWarningStatus=emailWarning.getText().equals(expectedemailWarning);
	    boolean telephoneWarningStatus=telephoneWarning.getText().equals(expectedtelephoneWarning);
		boolean passwordWarningStatus=passwordWarning.getText().equals(expectedpasswordWarning);
		
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;
	}
}
