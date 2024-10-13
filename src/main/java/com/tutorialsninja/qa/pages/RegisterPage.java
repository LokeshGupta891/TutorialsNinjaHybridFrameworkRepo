package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacypolicywarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstnamewarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastnamewarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailwarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneNumberwarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordwarning;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText)
	{
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText)
	{
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String EmailAddressText)
	{
		emailAddressField.sendKeys(EmailAddressText);
	}
	
	public void entertelephoneNumber(String telephoneNumber)
	{
		telephoneField.sendKeys(telephoneNumber);
	}
	
	public void enterpassword(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String confirmText)
	{
		passwordConfirmField.sendKeys(confirmText);
	}
	
	public void selectprivacyPolicy()
	{
		privacyPolicyField.click();
	}
	
	public AccountSuccessPage clickOnContinueButton()
	{
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void yesNewsLetterOption() {
		yesNewsLetterOption.click();
	}
	
	public String retrieveDuplicateEmailWarning()
	{
		String duplicateEmailwarningmessage = duplicateEmailAddressWarning.getText();
		return duplicateEmailwarningmessage;
	}
	
	public String retrievePrivacyPolicyWarning()
	{
		String privacypolicywarningText = privacypolicywarning.getText();
		return privacypolicywarningText;
	}
	
	public String retrievefirstnamewarning()
	{
		String firstnamewarningText = firstnamewarning.getText();
		return firstnamewarningText;
	}
	
	public String retrievelastnamewarning()
	{
		String lastnamewarningText = lastnamewarning.getText();
		return lastnamewarningText;
	}
	
	public String retrieveemailwarning()
	{
		String emailwarningText = emailwarning.getText();
		return emailwarningText;
	}
	
	public String retrievetelephoneNumberwarning()
	{
		String telephoneNumberwarningText = telephoneNumberwarning.getText();
		return telephoneNumberwarningText;
	}
	
	public String retrievepasswordwarning()
	{
		String passwordwarningText = passwordwarning.getText();
		return passwordwarningText;
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String lastNameText,String EmailAddressText,String telephoneNumber,String passwordText)
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(EmailAddressText);
		telephoneField.sendKeys(telephoneNumber);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String firstNameText,String lastNameText,String EmailAddressText,String telephoneNumber,String passwordText)
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(EmailAddressText);
		telephoneField.sendKeys(telephoneNumber);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		yesNewsLetterOption.click();
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	
	public boolean displayStatusofWarningMessages(String expectedprivacypolicy,String expectedfirstname,String expectedlastname,String expectedemail,String expectedtelephoneNumbe,String expectedpassword)
	{
		boolean privacypolicywarningstatus = privacypolicywarning.getText().contains(expectedprivacypolicy);
		boolean firstnamewarningstatus = firstnamewarning.getText().contains(expectedfirstname);
		boolean lastnamewarningstatus = lastnamewarning.getText().contains(expectedlastname);
		boolean emailwarningstatus = emailwarning.getText().contains(expectedemail);
		boolean telephoneNumberwarningstatus = telephoneNumberwarning.getText().contains(expectedtelephoneNumbe);
		boolean passwordwarningstatus = passwordwarning.getText().contains(expectedpassword);
		return privacypolicywarningstatus && firstnamewarningstatus && lastnamewarningstatus && emailwarningstatus && telephoneNumberwarningstatus && passwordwarningstatus;
	}
	
}
