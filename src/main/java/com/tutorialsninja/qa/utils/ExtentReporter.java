package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generateExtentReports()
	{
		ExtentReports extentreport = new ExtentReports();
		File extentreportfile = new File(System.getProperty("user.dir")+"//test-output//ExtentReports//extentreport.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(extentreportfile);

		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Tutorials Ninja Test Automation Results");
		spark.config().setDocumentTitle("Test Automation Report");
		spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentreport.attachReporter(spark);
		
		
		Properties configprop = new Properties();
		File file = new File(System.getProperty("user.dir")+"//src//main//java//com//tutorials//qa//config//config.properties");
		try
		{
		FileInputStream fisconfigprop= new FileInputStream(file);
		configprop.load(fisconfigprop);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		extentreport.setSystemInfo("Application URL",configprop.getProperty("url"));
		extentreport.setSystemInfo("Application BrowserName",configprop.getProperty("BrowserName"));
		extentreport.setSystemInfo("Application Email",configprop.getProperty("validEmailAddress"));
		extentreport.setSystemInfo("Application Password",configprop.getProperty("validPassword"));	
		extentreport.setSystemInfo("Operatinh System",System.getProperty("os.name"));
		extentreport.setSystemInfo("User Name",System.getProperty("user.name"));
		extentreport.setSystemInfo("Java Version used",System.getProperty("java.version"));

		return extentreport;
	}

}
