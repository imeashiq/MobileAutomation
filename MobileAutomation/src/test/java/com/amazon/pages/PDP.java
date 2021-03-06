package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.reusable.components.TestDataReader;
import com.reusable.components.WebElementBaseClass;

import io.qameta.allure.Step;

public class PDP extends WebElementBaseClass {

	TestDataReader testData;
	WebDriver driver;

	public static String productTitle = "ID:title_feature_div";
	public static String productPrice = "ID:newPitchPriceWrapper_feature_div";
	public static String addToCart = "ID:add-to-cart-button";
	public static String cartIcon = "ID:com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_count";
	public static String checkoutBtn = "ID:huc-proceed-to-checkout-button";

	// Contructor to create object for TestData Reader and get WebDriver
	public PDP(WebDriver driver) {
		testData = new TestDataReader();
		this.driver = driver;
	}

	/*
	 * Add the product to cart
	 */
	@Step("Add the product to cart")
	public String[] addItemToCart() {
		String strProductTitle = getElement(driver, productTitle, 15).getText();
		String strPrice = scrollAndSearchElement(driver, productPrice, 10, Direction.UP, 5).getText();
		scrollAndSearchElement(driver, addToCart, 10, Direction.UP, 5).click();
		Assert.assertTrue(getElement(driver, checkoutBtn, 10).isDisplayed(),
				"Checkout button should be displayed after adding item.");
		return new String[] { strProductTitle, strPrice };
	}

	/*
	 * Navigate to cart
	 */
	@Step("Navigate to cart from PDP")
	public void navigateToCart() {
		getElement(driver, cartIcon, 10).click();
	}
}
