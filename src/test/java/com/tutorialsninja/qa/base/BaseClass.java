package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class BaseClass {
	
	WebDriver driver;
	
	public Properties prop;
	public Properties dataProp;
	public BaseClass()
	{		
		prop= new Properties();
		File propfile = new File(System.getProperty("user.dir")+"//src//main//java//com//tutorials//qa//config//config.properties");
		
		dataProp = new Properties();
		File Datapropfile = new File(System.getProperty("user.dir")+"//src//main//java//com//tutorials//qa//testdata//testdata.properties");
		
		try
		{
			FileInputStream Datafis = new FileInputStream(Datapropfile);
			dataProp.load(Datafis);
		} 
		catch(Throwable e)
		{
			e.getStackTrace();
		}
		
		try
		{
			FileInputStream fis = new FileInputStream(propfile);
			prop.load(fis);
		}
		catch(Throwable e)
		{
			e.getStackTrace();
		}
	}
	
	public WebDriver initializeBrowserAndOpenApplication(String browsername)
	{
		if(browsername.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if (browsername.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if (browsername.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else if (browsername.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_TIMEOUT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
}
