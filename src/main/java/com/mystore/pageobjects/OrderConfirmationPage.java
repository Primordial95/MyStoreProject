package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class OrderConfirmationPage extends BaseClass{
		
	@FindBy(xpath = "//h1[@class='page-title']//span")
	private WebElement orderConfirmationText;
	
	public OrderConfirmationPage()
	{
		PageFactory.initElements(driver, this);
	}

	public String validateConfirmMessage()
	{
		String confirmMsg=orderConfirmationText.getText();
		return confirmMsg;
	}
}
