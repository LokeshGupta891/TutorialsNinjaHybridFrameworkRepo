package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropdownMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(name="search")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void clickonMyAccount()
	{
		myAccountDropdownMenu.click();
	}
	
	public LoginPage selectLoginOption()
	{
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		myAccountDropdownMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	
	public LoginPage navigateToLoginPage()
	{
		myAccountDropdownMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage clickonRegister()
	{
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterProductIntoSearchBoxField(String productText)
	{
		searchBoxField.sendKeys(productText);
	}
	
	public SearchPage clickonSearchButton()
	{
		searchButton.click();
		return new SearchPage(driver);
	}
	
	public SearchPage searchforaProduct(String productText)
	{
		searchBoxField.sendKeys(productText);
		searchButton.click();
		return new SearchPage(driver);
	}
	
}
