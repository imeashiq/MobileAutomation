package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.reusable.components.WebElementBaseClass;

public class PDP extends WebElementBaseClass {

	public static String productTitle = "ID:title_feature_div";
	public static String productPrice = "ID:newPitchPriceWrapper_feature_div";
	public static String addToCart = "ID:add-to-cart-button";
	public static String cartIcon = "ID:com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_count";
	public static String checkoutBtn = "ID:huc-proceed-to-checkout-button";

	/*
	 * Add the product to cart
	 */
	public String[] addItemToCart(WebDriver driver) {
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
	public void navigateToCart(WebDriver driver) {
		getElement(driver, cartIcon, 10).click();
	}
}
