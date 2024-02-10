package com.mystore.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class CheckoutPage extends BaseClass {

	@FindBy(xpath = "//button[@data-trigger='authentication']")
	private WebElement signInBtn;

	@FindBy(id = "login-email")
	private WebElement emailBox;

	@FindBy(id = "login-password")
	private WebElement passwordBox;

	@FindBy(xpath = "(//span[text()='Sign In'])[2]")
	private WebElement loginBtn;

	@FindBy(xpath = "//input[@type='radio']")
	private List<WebElement> shippingMethodBtn;

	@FindBy(xpath = "//span[text()='Next']")
	private WebElement proccedToPayment;

	@FindBy(xpath = "//span[text()='Order Summary']")
	private WebElement checkoutPageVerifyElement;

	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}

	public void signIn(String email, String password) {
		actionDriver.explicitWaitToClick(driver, 10, signInBtn);
		actionDriver.click(driver, signInBtn);
//		actionDriver.fluentWaitElement(driver, 10, emailBox);
		actionDriver.type(emailBox, email);
		actionDriver.type(emailBox, password);
		actionDriver.click(driver, loginBtn);
	}

	public void chooseShippingRate(int number) {
		actionDriver.clickonElements(shippingMethodBtn, number);
	}

	public ReviewPaymentPage proceedToPayments() {
		actionDriver.click(driver, proccedToPayment);
		return new ReviewPaymentPage();
	}

	public boolean verifyCheckoutPage() {
		return actionDriver.isDisplayed(checkoutPageVerifyElement);
	}

}
