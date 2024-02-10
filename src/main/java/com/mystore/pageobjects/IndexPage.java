package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;

public class IndexPage extends BaseClass {
	
	
	
	@FindBy(xpath = "//a[@class='logo']//img")
	private WebElement myStoreLogo;

	@FindBy(xpath = "//a[text()='Create an Account']")
	private WebElement registerBtn;

	@FindBy(xpath = "//a[contains(text(),'Sign In')]")
	private WebElement signinBtn;

	@FindBy(id = "search")
	private WebElement searchBox;

	@FindBy(xpath = "//div[@class='block block-search']//button")
	private WebElement searchBtn;

	@FindBy(xpath = "//span[@class='logged-in']")
	private WebElement loginCheckElement;

	@FindBy(xpath = "//button[@class='action switch']")
	private WebElement downBtn;
	
	@FindBy(xpath = "//a[text()='My Account']")
	private WebElement accountBtn;

	public IndexPage() {
		PageFactory.initElements(driver, this);
	}

	public LoginPage clickOnSignIn() {
		actionDriver.click(driver, signinBtn);
		return new LoginPage();
	}

	public boolean validateLogo() {
		return actionDriver.isDisplayed(myStoreLogo);
	}

	public String getMyStoreTitle() {
		return actionDriver.getTitle(driver);
	}

	public SearchResultPage searchProduct(String prodcutName) {
		actionDriver.type(searchBox, prodcutName);
		actionDriver.pressEnter(searchBox);
		return new SearchResultPage();
	}

	public RegisterPage registerUser() {
		actionDriver.click(driver, registerBtn);
		return new RegisterPage();
	}

	public MyAccountPage goToAccountPage()
	{
		actionDriver.fluentWaitElement(driver, 10, loginCheckElement);
		if (actionDriver.isDisplayed(loginCheckElement)) {
			actionDriver.click(driver, downBtn);
			actionDriver.click(driver, accountBtn);
			return new MyAccountPage();
		}
		else {
			return null;
		}
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
}
