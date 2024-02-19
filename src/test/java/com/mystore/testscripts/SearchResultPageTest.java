package com.mystore.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.SearchResultPage;

public class SearchResultPageTest extends BaseClass {
	private IndexPage indexPage;
	private SearchResultPage searchResultPage;

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
	public void checkProductAvailability() {
		searchResultPage = indexPage.searchProduct("shirt");
		boolean result = searchResultPage.isProductAvailable();
		Assert.assertTrue(result);
	}

	@Test(groups = {"Smoke","Sanity"})
	public void checkNoOfProduct() {
		searchResultPage = indexPage.searchProduct("shirt");
		System.out.println(searchResultPage.noOfProducts());
	}
}
