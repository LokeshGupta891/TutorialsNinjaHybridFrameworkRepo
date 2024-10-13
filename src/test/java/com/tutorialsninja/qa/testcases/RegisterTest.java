package com.tutorialsninja.qa.testcases;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends BaseClass{
	
	public WebDriver driver;
	
	RegisterPage registerPage;
	AccountSuccessPage accountsuccessPage;
	
	public RegisterTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplication(prop.getProperty("BrowserName"));
		HomePage homepage = new HomePage(driver);
		registerPage = homepage.navigateToRegisterPage();
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields()
	{
		accountsuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailwithTimestamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		
		String actualsuccessheading = accountsuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualsuccessheading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"account success page is not displayed");
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountProvidingAllFields()
	{
		accountsuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailwithTimestamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		
		String actualsuccessheading = accountsuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualsuccessheading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"account success page is not displayed");
	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountProvidingExistingEmailaddress()
	{
		accountsuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),prop.getProperty("validEmailAddress"), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
			
		String actualwarningmessage = registerPage.retrieveDuplicateEmailWarning();
		Assert.assertTrue(actualwarningmessage.contains(dataProp.getProperty("duplicateEmailWarning")),"warning message is not displayed with existing email");
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails()
	{
		registerPage.clickOnContinueButton();
		Assert.assertTrue(registerPage.displayStatusofWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailAddressWarning"), dataProp.getProperty("telephoneNumberWarning"), dataProp.getProperty("passwordWarning")), "not matching");
				
	}
}
