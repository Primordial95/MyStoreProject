package com.mystore.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.MyAccountPage;

public class AccountPageTets extends BaseClass {
	private IndexPage indexPage = new IndexPage();
	private MyAccountPage myAccountPage;
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

	private void goToAccountPage() {
		loginPage = indexPage.clickOnSignIn();
		indexPage = loginPage.login(properties.getProperty("user"), properties.getProperty("pass"));
		myAccountPage = indexPage.goToAccountPage();
	}

	@Test(groups = {"Smoke","Sanity"})
	public void verifyAccountPage() {
		goToAccountPage();
		String urlCurrent = myAccountPage.getCurrentUrl();
		String urlExpected = "https://magento.softwaretestingboard.com/customer/account/";
		Assert.assertEquals(urlCurrent, urlExpected);
	}

	@Test(groups = {"Smoke","Sanity"})
	public void verifyMyOrder() {
		goToAccountPage();
		Boolean result = myAccountPage.validateMyOrders();
		Assert.assertTrue(result);
	}

	@Test(groups = {"Smoke","Sanity"})
	public void verifyPaymentTab() {
		goToAccountPage();
		boolean result = myAccountPage.validateStoredPayment();
		Assert.assertTrue(result);
	}
}
