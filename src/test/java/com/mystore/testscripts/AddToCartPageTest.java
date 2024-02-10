package com.mystore.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.CheckoutPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.SearchResultPage;

public class AddToCartPageTest extends BaseClass {
	private IndexPage indexPage;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;
	private CheckoutPage checkoutPage;

	@BeforeMethod
	public void setup() {
		loadActionDriver();
		launchApp();
		indexPage = new IndexPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	private void intialsetup() {
		searchResultPage = indexPage.searchProduct("shirt");
		addToCartPage = searchResultPage.clickOnProduct(1);
		addToCartPage.selectSize(2);
		addToCartPage.selectColour(1);
		addToCartPage.selectQuantity("2");
		addToCartPage.clickOnAddToCart();
	}

	@Test
	public void addCartTest() {
		intialsetup();
		boolean result = addToCartPage.validateAddedToCart();
		Assert.assertTrue(result);
	}

	@Test
	public void navigateToCheckoutPageTest() {
		intialsetup();
		checkoutPage = addToCartPage.toCheckout();
		Assert.assertTrue(checkoutPage.verifyCheckoutPage());
	}
}
