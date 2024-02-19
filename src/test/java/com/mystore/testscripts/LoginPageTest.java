package com.mystore.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.RegisterPage;

public class LoginPageTest extends BaseClass {
	private IndexPage indexPage;
	private LoginPage loginPage;
	private RegisterPage registerPage;

	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup() {
		loadActionDriver();
		launchApp();
		indexPage = new IndexPage();
	}

	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}

	@Test(groups = {"Smoke","Sanity"})
	public void loginTest() {
		loginPage = indexPage.clickOnSignIn();
		indexPage = loginPage.login(properties.getProperty("user"), properties.getProperty("pass"));
		String urlCurrent = indexPage.getCurrentUrl();
		String urlExpec = "https://magento.softwaretestingboard.com/";
		Assert.assertEquals(urlCurrent, urlExpec);
	}

	@Test(groups = "Smoke")
	public void verifyNewAccount() {
		loginPage = indexPage.clickOnSignIn();
		registerPage = loginPage.createNewAccount();
		String urlCurrent = registerPage.getCurrentUrl();
		String urlExpec = "https://magento.softwaretestingboard.com/customer/account/create/";
		Assert.assertEquals(urlCurrent, urlExpec);
	}
}
