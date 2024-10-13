package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends BaseClass{

	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;
	
	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
	    driver = initializeBrowserAndOpenApplication(prop.getProperty("BrowserName"));
	    HomePage homepage = new HomePage(driver);
	    loginPage = homepage.navigateToLoginPage();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority=1,dataProvider = "ValidCredentailsSupplier")
	public void VerifyLoginWithValidCredentials(String email,String password) throws InterruptedException
	{
		accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfAccountInfoOption(),"account info option is not displayed");
	}
	
	@DataProvider(name="ValidCredentailsSupplier")
	public Object[][] supplyData()
	{
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	
	@Test(priority=2)
	public void VerifyLoginWithInValidCredentials() throws InterruptedException
	{
		
		loginPage.login(Utilities.generateEmailwithTimestamp(),dataProp.getProperty("invalidPassword"));
		String actualwarningmessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedwarningmessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualwarningmessage.contains(expectedwarningmessage),"Expected warning message not displayed");
	}
	
	@Test(priority=3)
	public void VerifyLoginWithInvalidEmailandValidPassword() throws InterruptedException
	{
		loginPage.login(Utilities.generateEmailwithTimestamp(), prop.getProperty("validPassword"));
		String actualwarningmessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedwarningmessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualwarningmessage.contains(expectedwarningmessage),"Expected warning message not displayed");
	}
	
	@Test(priority=4)
	public void VerifyLoginWithValidEmailandInValidPassword() throws InterruptedException
	{
		loginPage.login(prop.getProperty("validEmailAddress"), dataProp.getProperty("invalidPassword"));
		String actualwarningmessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedwarningmessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualwarningmessage.contains(expectedwarningmessage),"Expected warning message not displayed");
		
	}
	
	@Test(priority=5)
	public void VerifyLoginWithInvalidCredentials() throws InterruptedException
	{	
		loginPage.clickOnLoginButton();
		String actualwarningmessage = loginPage.retrieveEmailPasswordNotMatchingWarningText();
		String expectedwarningmessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualwarningmessage.contains(expectedwarningmessage),"Expected warning message not displayed");
		Thread.sleep(2000);
	}
}
