package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends BaseClass{
	
	public WebDriver driver;
	SearchPage searchpage;
	HomePage homepage;
	
	public SearchTest()
	{
		super();
	}

	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplication(prop.getProperty("BrowserName"));
		homepage = new HomePage(driver);
	}
	
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	
	
	@Test(priority=1)
	public void verifySearchWithValidProduct()
	{
		searchpage = homepage.searchforaProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchpage.DisplayStatusOfHPValidProduct(),"Valid product HP is not displayed");
	}
	
	@Test(priority=2)
	public void verifySearchWithInValidProduct()
	{
		searchpage = homepage.searchforaProduct(dataProp.getProperty("invalidProduct"));
		String actualSearchMessage = searchpage.retrieveNoProductMessageText();
		Assert.assertTrue(actualSearchMessage.contains(dataProp.getProperty("noProductTextinSearchResults")), "no product search message is not displayed");
	}
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct()
	{
		searchpage = homepage.clickonSearchButton();	
		String actualSearchMessage = searchpage.retrieveNoProductMessageText();
		Assert.assertTrue(actualSearchMessage.contains(dataProp.getProperty("noProductTextinSearchResults")), "no product search message is not displayed");
	}
	
	
	
}
