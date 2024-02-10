package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class LoginPage extends BaseClass{
			
		@FindBy(id = "email")
		private WebElement userName;
		
		@FindBy(id = "pass")
		private WebElement userPass;
		
		@FindBy(id = "send2")
		private WebElement signInBtn;
		
		@FindBy(xpath = "//span[text()='Create an Account']")
		private WebElement createNewAccountBtn;
		
		public LoginPage()
		{
			PageFactory.initElements(driver, this);
		}
		
		public IndexPage login(String uName,String password)
		{
			actionDriver.type(userName, uName);
			actionDriver.type(userPass, password);
			actionDriver.click(driver, signInBtn);
			return new IndexPage();
		}
		
		public RegisterPage createNewAccount()
		{
			actionDriver.click(driver, createNewAccountBtn);
			return new RegisterPage();
		}
		
		public String currentUrl()
		{
			return driver.getCurrentUrl();
		}
}
