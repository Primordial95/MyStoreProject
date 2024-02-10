package com.mystore.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class ReviewPaymentPage extends BaseClass {

	@FindBy(xpath = "(//table[@class='data table table-totals']/tbody/tr//span[@class='price'])[position()<last()]")
	private List<WebElement> prices;
	
	@FindBy(xpath = "//table[@class='data table table-totals']//tr[last()]//span")
	private WebElement totalPrice;

	@FindBy(xpath = "//button[@class='action primary checkout']")
	private WebElement placeOrderBtn;

	@FindBy(xpath = "//div[text()='Payment Method']")
	private WebElement verifyPageElement;
	
	@FindBy(xpath = "//img[@alt='Loading...']")
	private WebElement loaderElement;

	public WebElement getLoaderElement() {
		return loaderElement;
	}

	public ReviewPaymentPage() {
		PageFactory.initElements(driver, this);
	}

	public double subtotalPrice() {
		String price = prices.get(0).getText().replace("$", "");
		double finalPrice = Double.parseDouble(price);// returns double value from string document
		return finalPrice;
	}

	public double discountAndShippingPrice() {
		if (prices.size()<3) {
			String price = prices.get(1).getText().replace("$", "");
			double finalPrice = Double.parseDouble(price);
			return finalPrice;
		}
		else {
			String price=prices.get(1).getText().replace("$", "")+prices.get(2).getText().replace("$", "");
			double finalPrice = Double.parseDouble(price);
			return finalPrice;
		}
	}
			

	public double orderTotal() {
		String price = totalPrice.getText().replace("$", "");
		double finalPrice = Double.parseDouble(price);
		return finalPrice;
	}

	public boolean validatePrice() {
		return actionDriver.validOrderTotal(orderTotal(),discountAndShippingPrice(), subtotalPrice());
	}

	public OrderConfirmationPage clickOnPlaceOrder() {
		actionDriver.explicitWaitToClick(driver, 10, placeOrderBtn);
		actionDriver.click(driver, placeOrderBtn);
		return new OrderConfirmationPage();
	}
	
	public boolean verfiyPaymentPage() {
		return actionDriver.isDisplayed(verifyPageElement);
	}
}
