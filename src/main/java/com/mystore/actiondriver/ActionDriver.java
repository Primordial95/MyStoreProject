package com.mystore.actiondriver;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mystore.actioninterface.ActionInterface;
import com.mystore.base.BaseClass;

public class ActionDriver extends BaseClass implements ActionInterface {

	@Override
	public void scrollByVisibility(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
	}

	@Override
	public void click(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}

	@Override
	public void pressEnter(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}

	@Override
	public boolean findElement(WebElement element) {
		boolean flag = false;
		try {
			element.isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Element found");
			} else {
				System.out.println("Element not found");
			}
		}
		return flag;
	}

	@Override
	public boolean isDisplayed(WebElement element) {
		boolean flag = false;
		flag = findElement(element);
		if (flag) {
			flag = element.isDisplayed();
			if (flag) {
				System.out.println("Element is Displayed");
			} else {
				System.out.println("Element not Displayed");
			}
		} else {
			System.out.println("Not displayed");
		}
		return flag;
	}

	@Override
	public boolean isSelected(WebElement element) {
		boolean flag = false;
		flag = findElement(element);
		if (flag) {
			flag = element.isSelected();
			if (flag) {
				System.out.println("Element is selected");
			} else {
				System.out.println("Element not selected");
			}
		} else {
			System.out.println("Not Selected");
		}
		return flag;
	}

	@Override
	public boolean isEnabled(WebElement element) {
		boolean flag = false;
		flag = findElement(element);
		if (flag) {
			flag = element.isEnabled();
			if (flag) {
				System.out.println("Element Enabled");
			} else {
				System.out.println("Element not Enabled");
			}
		} else {
			System.out.println("Element not Enabled");
		}
		return flag;
	}

	@Override
	public boolean type(WebElement element, String value) {
		boolean flag = false;
		flag = element.isEnabled();
		if (flag) {
			element.clear();
			element.sendKeys(value);
			System.out.println("Successfully entered value");
		} else {
			System.out.println("Unable to enter");
		}
		return flag;
	}

	@Override
	public boolean selectByIndex(WebElement element, int index) {
		boolean flag = false;
		try {
			Select select = new Select(element);
			select.selectByIndex(index);
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Option selected by index");
			} else {
				System.out.println("Option not selected by index");
			}
		}
		return flag;
	}

	@Override
	public boolean selectByValue(WebElement element, String value) {
		boolean flag = false;
		try {
			Select select = new Select(element);
			select.selectByValue(value);
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("option selected by value");
			} else {
				System.out.println("Options not selected by value");
			}
		}
		return flag;
	}

	@Override
	public boolean selectByVisibleText(WebElement element, String visibleText) {
		boolean flag = false;
		try {
			Select select = new Select(element);
			select.selectByVisibleText(visibleText);
			flag = true;
		} catch (Exception e) {
		} finally {
			if (flag) {
				System.out.println("Option selected by visible text");
			} else {
				System.out.println("Options not selected by visible text");
			}
		}
		return flag;
	}

	@Override
	public boolean jsClick(WebDriver driver, WebElement element) {
		boolean flag = false;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", element);
			flag = true;
		} catch (Exception e) {
		} finally {
			if (flag) {
				System.out.println("Click performed");
			} else {
				System.out.println("Click not performed");
			}
		}
		return flag;
	}

	@Override
	public boolean mouseOverElement(WebDriver driver, WebElement element) {
		boolean flag = false;
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
			flag = true;
		} catch (Exception e) {
		} finally {
			if (flag) {
				System.out.println("Action performed");
			} else {
				System.out.println("Action not performed");
			}
		}
		return flag;
	}

	@Override
	public boolean draggable(WebDriver driver, WebElement element, int x, int y) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDropBy(element, x, y).perform();
			flag = true;
		} catch (Exception e) {
		} finally {
			if (flag) {
				System.out.println("Drag performed");
			} else {
				System.out.println("Drag not performed");
			}
		}
		return flag;
	}

	@Override
	public boolean rightClick(WebDriver driver, WebElement element) {
		boolean flag = false;
		try {
			new Actions(driver).contextClick(element).perform();
			flag = true;
		} catch (Exception e) {
		} finally {
			if (flag) {
				System.out.println("rightclick perfromed");
			} else {
				System.out.println("right click performed");
			}
		}
		return flag;
	}

	@Override
	public boolean switchWindowByIndex(WebDriver driver, int index) {
		boolean flag = false;
		try {
			Set<String> handles = driver.getWindowHandles();
//			Object[] o=handles.toArray();
//			driver.switchTo().window(o[0].toString());
//			above also can be done
			String[] array = handles.toArray(new String[0]);
			driver.switchTo().window(array[index - 1]);
			flag = true;
		} catch (Exception e) {
		} finally {
			if (flag) {
				System.out.println("Window switched");
			} else {
				System.out.println("Windows not switched");
			}
		}
		return flag;
	}

	/**
	 * index starts from 1 as it shows 1st window
	 */
	@Override
	public int getColumnCount(WebElement element) {
		try {
			List<WebElement> columns = element.findElements(By.tagName("th"));
			int c = columns.size();
			return c;
		} catch (Exception e) {
			return 0;
		}

	}

	@Override
	public int getRowCount(WebElement element) {
		try {
			List<WebElement> elements = element.findElements(By.tagName("tr"));
			int r = elements.size();
			return r;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int getCellsPresent(WebElement element) {
		try {
			List<WebElement> elements = element.findElements(By.xpath("//th|//td"));
			int d = elements.size();
			return d;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean alert() {
		boolean flag = false;
		Alert alert = null;
		try {
			alert = driver.switchTo().alert();
			alert.accept();
			flag = true;
		} catch (Exception e) {
		} finally {
			if (flag) {
				System.out.println("Alert is handled");
			} else {
				System.out.println("There is not alert");
			}
		}
		return flag;
	}

	@Override
	public boolean launchUrl(WebDriver driver, String url) {
		boolean flag = false;
		try {
			driver.navigate().to(url);
			flag = true;
		} catch (Exception e) {
		} finally {
			if (flag) {
				System.out.println("Success");
			} else {
				System.out.println("Failed");
			}
		}
		return flag;
	}

	@Override
	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	@Override
	public void implicitWait(WebDriver driver, int timeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
	}

	@Override
	public void explicitWaitToClick(WebDriver driver, int timeout, WebElement element) {
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		driverWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	@Override
	public void waitUntilInvisible(WebDriver driver, int timeout, WebElement element) {
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		driverWait.until(ExpectedConditions.visibilityOf(element));
		driverWait.until(ExpectedConditions.invisibilityOf(element));
	}

	@Override
	public void pageLoadTimeouts(WebDriver driver, int timeout) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
	}

	@Override
	public void fluentWait(WebDriver driver, String url, int timeout) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(2))
				.ignoring(Exception.class);
		fluentWait.until(ExpectedConditions.urlToBe(url));
	}

	@Override
	public void fluentWaitElement(WebDriver driver, int timeout, WebElement element) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(2))
				.ignoring(Exception.class);
		fluentWait.until(ExpectedConditions.visibilityOf(element));
	}

	@Override
	public String currentTime() {
		Date date = new Date();
		return date.toString().replace(":", "_").replace(" ", "_");
	}

	@Override
	public void screenShot(WebDriver driver, String filename) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(".\\Screenshots\\" + currentTime() + filename + ".png");
		try {
			FileUtils.copyFile(src, des);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int productsOnSearch(List<WebElement> products) {
		return products.size();
	}

	@Override
	public void clickonElements(List<WebElement> products, int productNumber) {
		try {
			products.get(productNumber - 1).click();
		} catch (Exception e) {
		}

	}

	@Override
	public boolean validOrderTotal(double total, double discountShipping, double subtotal) {
		if (total == (subtotal + discountShipping)) {
			System.out.println("Validated");
			return true;
		} else {
			System.out.println("Not Validated");
			return false;
		}
	}
}
