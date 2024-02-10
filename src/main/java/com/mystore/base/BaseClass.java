package com.mystore.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.mystore.actiondriver.ActionDriver;

public class BaseClass {
	public static Properties properties;
	public static WebDriver driver;
	public static ActionDriver actionDriver;


	@BeforeTest
	public void loadConfig() {
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
		driver.get(properties.getProperty("url"));
		actionDriver = new ActionDriver();
		actionDriver.implicitWait(driver, 10);
		actionDriver.pageLoadTimeouts(driver, 10);
	}

}
