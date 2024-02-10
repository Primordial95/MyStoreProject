package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class MyAccountPage extends BaseClass {

	@FindBy(xpath = "//a[text()='My Orders']")
	private WebElement myOrders;

	@FindBy(xpath = "//a[text()='Stored Payment Methods']")
	private WebElement storedPayment;

	public MyAccountPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean validateMyOrders() {
		return actionDriver.isDisplayed(myOrders);
	}

	public boolean validateStoredPayment() {
		actionDriver.fluentWaitElement(driver, 10, storedPayment);
		return actionDriver.isDisplayed(storedPayment);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
}
