package com.mystore.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mystore.base.BaseClass;

public class SearchResultPage extends BaseClass {

	@FindBy(xpath = "//li[@class='item product product-item']//div[@class='product-item-info'][1]")
	private List<WebElement> productList;

	public SearchResultPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isProductAvailable() {
		try {
			return actionDriver.isDisplayed(productList.get(0));
		} catch (Exception e) {
			System.out.println("No Products Listed");
			return false;
		}
	}

	public WebElement isProductvisible() {
			return productList.get(0);
	}

	public int noOfProducts() {
		return actionDriver.productsOnSearch(productList);
	}

	public AddToCartPage clickOnProduct(int productNo) {
		actionDriver.fluentWaitElement(driver, 10, productList.get(productNo));
		actionDriver.clickonElements(productList, productNo);
		return new AddToCartPage();
	}
}
