package com.reusable.components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class WebElementBaseClass {

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	};

	/*
	 * Method is to find with WebElement with Expilicit Wait and return it.
	 */

	public WebElement getElement(WebDriver driver, String element, int timeOut) {

		// Create wait with given wait time passed as argument
		WebDriverWait wait = new WebDriverWait(driver, timeOut);

		// Return Element
		WebElement rElement = null;

		By locator = null;
		try {
			String[] divider = element.split(":", 2);
			String locatorBy = divider[0];
			String locatorValue = divider[1];

			switch (locatorBy.toUpperCase()) {
			case "XPATH":
				locator = By.xpath(locatorValue);
				break;
			case "ID":
				locator = By.id(locatorValue);
				break;
			case "NAME":
				locator = By.name(locatorValue);
				break;
			}
			rElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

		return rElement;
	}

	public WebElement scrollAndSearchElement(WebDriver driver, String element, int timeOut, Direction direction,
			int noOfScroll) {
		WebElement rElement = null;
		for (int i = 0; i < noOfScroll; i++) {
			try {
				swipe(driver, direction);
				rElement = getElement(driver, element, timeOut);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("Trying to find element in next swipe...");
			}
		}
		return rElement;
	}

	public void swipe(WebDriver driver, Direction direction) {
		String strStartCoordinates = null;
		String strEndCoordinates = null;

		switch (direction) {
		case DOWN:
			strStartCoordinates = "50%,25%";
			strEndCoordinates = "50%,80%";
			break;
		case LEFT:
			strStartCoordinates = "80%,60%";
			strEndCoordinates = "10%,60%";
			break;
		case RIGHT:
			strStartCoordinates = "30%,50%";
			strEndCoordinates = "80%,50%";
			break;
		case UP:
			strStartCoordinates = "50%,80%";
			strEndCoordinates = "50%,25%";
			break;
		default:
			strStartCoordinates = "50%,70%";
			strEndCoordinates = "50%,45%";
			break;
		}
		TouchAction touchAction = new TouchAction((AndroidDriver) driver);
		Dimension size = driver.manage().window().getSize();

		String[] startTemp = strStartCoordinates.split(",");
		String[] endTemp = strEndCoordinates.split(",");
		int startx = Integer.parseInt((startTemp[0].replaceAll("%", "").trim()));
		startx = (int) ((size.width * startx) / 100);

		int starty = Integer.parseInt((startTemp[1].replaceAll("%", "").trim()));
		starty = (int) (size.height * starty / 100);

		int endx = Integer.parseInt((endTemp[0].replaceAll("%", "").trim()));
		endx = (int) (size.width * endx / 100);

		int endy = Integer.parseInt((endTemp[1].replaceAll("%", "").trim()));
		endy = (int) (size.height * endy / 100);

		touchAction.press(PointOption.point(startx, starty)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(0)))
				.moveTo(PointOption.point(endx, endy)).release().perform();
	}

	public String replaceDynamicLocator(String element, String replacementValue) {
		return element.replace("<<<>>>", replacementValue);
	}

	public void pressKeyEvent(WebDriver driver, AndroidKey key) {
		((AndroidDriver) driver).pressKey(new KeyEvent(key));
	}
}
