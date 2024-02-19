package com.mystore.testscripts;

import org.testng.Assert;
import org.testng.annotations.*;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.RegisterPage;
import com.mystore.utility.Log;

public class IndexPageTest extends BaseClass {
	private IndexPage indexPage;
	private RegisterPage registerPage;
	private LoginPage loginPage;

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

	@Test(groups = "Smoke")
	public void verifyLogo() {
		Log.startTestCase("verifyLogo");
		boolean result = indexPage.validateLogo();
		Log.info("Verifying if the logo is present");
		Assert.assertTrue(result);
		Log.info("Logo is present");
		Log.endTestCase("verifyLogo");
	}

	@Test(groups = "Smoke")
	public void verifyTitle() {
		Log.startTestCase("verifyTitle");
		Log.info("Getting my Store Title");
		String title = indexPage.getMyStoreTitle();
		Log.info("Verifying if StoreTitle is same as expected");
		Assert.assertEquals(title, "Home Page");
		Log.info("Verified Store Title");
		Log.endTestCase("verifyTitle");
	}

	@Test(groups = "Smoke")
	public void verifyRegisterUserBtn() {
		Log.startTestCase("verifyRegisterUserBtn");
		Log.info("clicking on Create an Account");
		registerPage = indexPage.registerUser();
		Log.info("Getting current Url");
//		actionDriver.fluentWait(driver, "https://magento.softwaretestingboard.com/customer/account/create/", 10); //for firefox
		String url = registerPage.getCurrentUrl();
		String expectedurl = "https://magento.softwaretestingboard.com/customer/account/create/";
		Log.info("Verifying if the Url matches the Expected Url");
		Assert.assertEquals(url, expectedurl);
		Log.info("Matches the expected Url");
		Log.endTestCase("verifyRegisterUserBtn");
	}

	@Test(groups = "Smoke")
	public void verifySignInbtn() {
		Log.startTestCase("verifySignInbtn");
		Log.info("clicking on signin button");
		loginPage = indexPage.clickOnSignIn();
		Log.info("getting current url");
		String actualUrl = loginPage.currentUrl();
		String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS8%2C/";
		Log.info("Verifying if the url matches the expected url");
		Assert.assertEquals(actualUrl, expectedUrl);
		Log.info("Match Success");
		Log.endTestCase("verifySignInbtn");
	}
}
