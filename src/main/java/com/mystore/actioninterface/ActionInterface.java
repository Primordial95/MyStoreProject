package com.mystore.actioninterface;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ActionInterface {
	public void scrollByVisibility(WebElement element);

	public void click(WebDriver driver, WebElement element);

	public void pressEnter(WebElement element);

	public boolean findElement(WebElement element);

	public boolean isDisplayed(WebElement element);

	public boolean isSelected(WebElement element);

	public boolean isEnabled(WebElement element);

	public boolean type(WebElement element, String value);

	public boolean selectByIndex(WebElement element, int index);

	public boolean selectByValue(WebElement element, String value);

	public boolean selectByVisibleText(WebElement element, String visibleText);

	public boolean jsClick(WebDriver driver, WebElement element);

	public boolean mouseOverElement(WebDriver driver, WebElement element);

	public boolean draggable(WebDriver driver, WebElement element, int x, int y);

	public boolean rightClick(WebDriver driver, WebElement element);

	public boolean switchWindowByIndex(WebDriver driver, int index);

	public int getColumnCount(WebElement element);

	public int getRowCount(WebElement element);

	public int getCellsPresent(WebElement element);

	public boolean isAlertPresent(WebDriver driver);

	public boolean launchUrl(WebDriver driver, String url);

	public String getTitle(WebDriver driver);

	public void implicitWait(WebDriver driver, int timeout);

	public void explicitWaitToClick(WebDriver driver, int timeout, WebElement element);

	public void pageLoadTimeouts(WebDriver driver, int timeout);

	public void fluentWait(WebDriver driver, String url, int timeout);

	public void fluentWaitElement(WebDriver driver, int timeout, WebElement element);

	public void waitUntilInvisible(WebDriver driver, int timeout, WebElement element);

	public String currentTime();

	public String screenShot(WebDriver driver, String filename);

	public int productsOnSearch(List<WebElement> products);

	public void clickonElements(List<WebElement> products, int productNumber);

	public boolean validOrderTotal(double total, double discountShipping, double subtotal);
}
