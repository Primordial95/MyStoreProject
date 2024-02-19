package com.mystore.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.CheckoutPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.ReviewPaymentPage;
import com.mystore.pageobjects.SearchResultPage;

public class CheckoutPageTest extends BaseClass {
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

	@Test(dataProvider = "ProductSpec", dataProviderClass = DataProviders.class,groups = "Regression")
	public void toPaymentPage(String productName,String size,String colour,String quantity,String shipping) {
		loginPage = indexPage.clickOnSignIn();
		indexPage = loginPage.login(properties.getProperty("user"), properties.getProperty("pass"));
		searchResultPage = indexPage.searchProduct(productName);
		addToCartPage = searchResultPage.clickOnProduct(1);
		addToCartPage.selectSize(Integer.parseInt(size)); 
		addToCartPage.selectColour(Integer.parseInt(colour));
		addToCartPage.selectQuantity(quantity);
		addToCartPage.clickOnAddToCart();
		checkoutPage = addToCartPage.toCheckout();
		checkoutPage.chooseShippingRate(Integer.parseInt(shipping));
		reviewPaymentPage = checkoutPage.proceedToPayments();
		Assert.assertTrue(reviewPaymentPage.verfiyPaymentPage());
	}

}
