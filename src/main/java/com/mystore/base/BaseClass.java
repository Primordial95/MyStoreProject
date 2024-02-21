package com.mystore.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.mystore.actiondriver.ActionDriver;
import com.mystore.utility.ExtentManager;

public class BaseClass {
	public static Properties properties;
	public static WebDriver driver;
	public static ActionDriver actionDriver;

	@BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
	public void loadConfig() {
		ExtentManager.setExtent();
		try {
			properties = new Properties();
			FileInputStream stream = new FileInputStream("..\\MyStoreProject\\Configuration\\config.properties");
			properties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public ActionDriver loadActionDriver() {
		if (actionDriver == null) {
			actionDriver = new ActionDriver();
		}
		return actionDriver;
	}

	public void launchApp() {
		String browserName = properties.getProperty("browser");
		if (browserName.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.contains("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.contains("IE")) {
			driver = new InternetExplorerDriver();
		}
//		driver.manage().window().maximize();
		driver.get(properties.getProperty("url"));
		actionDriver = new ActionDriver();
		actionDriver.implicitWait(driver, 10);
		actionDriver.pageLoadTimeouts(driver, 10);
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		ExtentManager.endReport();
		System.out.println("End of the report");
	}

}
