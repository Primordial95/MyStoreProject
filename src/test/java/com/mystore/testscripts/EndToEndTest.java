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
import com.mystore.pageobjects.OrderConfirmationPage;
import com.mystore.pageobjects.ReviewPaymentPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.utility.Log;

public class EndToEndTest extends BaseClass {
	private IndexPage indexPage;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;
	private CheckoutPage checkoutPage;
	private LoginPage loginPage;
	private ReviewPaymentPage reviewPaymentPage;
	private OrderConfirmationPage orderConfirmationPage;

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
	public void endToEndTest(String productName,String size,String colour,String quantity,String shipping) {
		Log.startTestCase("End To End Test");
		Log.info("click on signIn");
		loginPage = indexPage.clickOnSignIn();
		Log.info("Getting user and pass from properties");
		indexPage = loginPage.login(properties.getProperty("user"), properties.getProperty("pass"));
		actionDriver.fluentWaitElement(driver, 10, indexPage.getAdElement());
		Log.info("Searching the Product");
		searchResultPage = indexPage.searchProduct(productName);
		Log.info("Clicking on Desired Product");
		addToCartPage = searchResultPage.clickOnProduct(1);
		Log.info("Selecting size");
		addToCartPage.selectSize(Integer.parseInt(size));
		Log.info("Selecting colour");
		addToCartPage.selectColour(Integer.parseInt(colour));
		Log.info("Selecting quantity");
		addToCartPage.selectQuantity(quantity);
		Log.info("Adding to Cart");
		addToCartPage.clickOnAddToCart();
		Log.info("Proceding to Checkout");
		checkoutPage = addToCartPage.toCheckout();
		Log.info("Choosing Shipping Rate");
		checkoutPage.chooseShippingRate(Integer.parseInt(shipping));
		Log.info("Proceeding To Payments");
		reviewPaymentPage = checkoutPage.proceedToPayments();
		Log.info("Clicking On Place Order Button");
		actionDriver.waitUntilInvisible(driver, 10, reviewPaymentPage.getLoaderElement());
		orderConfirmationPage = reviewPaymentPage.clickOnPlaceOrder();
		Log.info("Validating Order Successfull message");
		actionDriver.waitUntilInvisible(driver, 10, reviewPaymentPage.getLoaderElement());
		String orderPlaced = orderConfirmationPage.validateConfirmMessage();
		Assert.assertEquals(orderPlaced, "Thank you for your purchase!");
		Log.info("Validation Successfull");
		Log.endTestCase("End To End Test");
	}
}
