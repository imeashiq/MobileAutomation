package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.reusable.components.WebElementBaseClass;

public class Cart extends WebElementBaseClass {

	public static String checkoutBtn = "ID:a-autoid-1";
	public static String genericXpath = "XPATH://*[@text=<<<>>>]";

	/*
	 * Verify product and price details
	 */
	public void verifyProductDetails(WebDriver driver, String[] productDetails) {
		String productTitle = productDetails[0];
		String productPrice = productDetails[1];
		Assert.assertTrue(getElement(driver, replaceDynamicLocator(genericXpath, productTitle), 15).isDisplayed(),
				"Product Title mismatches");
		Assert.assertTrue(getElement(driver, replaceDynamicLocator(genericXpath, productPrice), 5).isDisplayed(),
				"Product Price mismatches");
	}

	/*
	 * Method to click and proceed to checkout
	 */
	public void proceedToCheckout(WebDriver driver) {
		getElement(driver, checkoutBtn, 15).click();
	}
}
