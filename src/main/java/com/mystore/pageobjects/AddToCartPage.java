package com.mystore.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class AddToCartPage extends BaseClass {

	@FindBy(xpath = "//div[@class='swatch-option text']")
	private List<WebElement> sizeList;

	@FindBy(xpath = "//div[@class='swatch-option color']")
	private List<WebElement> colorList;

	@FindBy(id = "qty")
	private WebElement quantity;

	@FindBy(id = "product-addtocart-button")
	private WebElement addToCartBtn;

	@FindBy(xpath = "//div[contains(text(),'You added ')]")
	private WebElement addedToCartMessage;

	@FindBy(id = "top-cart-btn-checkout")
	private WebElement proceedToCheckout;

	@FindBy(xpath = "(//div[@class=\"minicart-wrapper\"]//a)[1]")
	private WebElement cartBtn;


	public AddToCartPage() {
		PageFactory.initElements(driver, this);
	}

	public void selectSize(int sizeByNumber) {
		actionDriver.clickonElements(sizeList, sizeByNumber);
	}

	public void selectColour(int colourByNumber) {
		actionDriver.clickonElements(colorList, colourByNumber);
	}

	public void selectQuantity(String noOfItems) {
		actionDriver.type(quantity, noOfItems);
	}

	public void clickOnAddToCart() {
		actionDriver.click(driver, addToCartBtn);
	}

	public boolean validateAddedToCart() {
		actionDriver.fluentWaitElement(driver, 350, addedToCartMessage);
		return actionDriver.isDisplayed(addedToCartMessage);
	}

	public WebElement getAddedToCartMessage() {
		return addedToCartMessage;
	}

	public CheckoutPage toCheckout() {
		actionDriver.fluentWaitElement(driver, 10, addedToCartMessage);
		actionDriver.click(driver, cartBtn);
		actionDriver.fluentWaitElement(driver, 10, proceedToCheckout);
		actionDriver.click(driver, proceedToCheckout);
		return new CheckoutPage();
	}
}
