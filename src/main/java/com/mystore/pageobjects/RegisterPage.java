package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class RegisterPage extends BaseClass {

	@FindBy(xpath = "//span[text()='Create New Customer Account']")
	private WebElement registerTitle;

	public RegisterPage() {
		PageFactory.initElements(driver, this);
	}

	public void validRegisterPage() {
		actionDriver.isDisplayed(registerTitle);
	}

	public String getCurrentUrl() {
		String urlString=driver.getCurrentUrl();
		return urlString;
	}
}
