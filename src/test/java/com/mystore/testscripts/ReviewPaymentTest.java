package com.mystore.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.CheckoutPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.ReviewPaymentPage;
import com.mystore.pageobjects.SearchResultPage;

public class ReviewPaymentTest extends BaseClass {
	private IndexPage indexPage;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;
	private CheckoutPage checkoutPage;
	private LoginPage loginPage;
	private ReviewPaymentPage reviewPaymentPage;

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

	@Test(groups = "Regression")
	public void validatePriceCalculations() {
		loginPage = indexPage.clickOnSignIn();
		indexPage = loginPage.login(properties.getProperty("user"), properties.getProperty("pass"));
		searchResultPage = indexPage.searchProduct("shirt");
		addToCartPage = searchResultPage.clickOnProduct(1);
		addToCartPage.selectSize(2);
		addToCartPage.selectColour(1);
		addToCartPage.selectQuantity("1");
		addToCartPage.clickOnAddToCart();
		checkoutPage = addToCartPage.toCheckout();
		checkoutPage.chooseShippingRate(2);
		reviewPaymentPage = checkoutPage.proceedToPayments();
		actionDriver.waitUntilInvisible(driver, 10, reviewPaymentPage.getLoaderElement());
		boolean result = reviewPaymentPage.validatePrice();
		Assert.assertTrue(result);
	}

}
