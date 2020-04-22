package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.reusable.components.WebElementBaseClass;

public class PMP extends WebElementBaseClass{
	
	// Locators used in this page
	public static String selectProductPMP = "XPATH://*[@instance='<<<>>>']";
	public static String productTitle = "ID:title_feature_div";

	/*
	 * Scroll and select a product from the search result dynamically
	 */
	public void selectProductFromSearchResult(WebDriver driver, int itemToBeSelected) {
		int intanceNumber = 39 + (itemToBeSelected * 8);
		scrollAndSearchElement(driver, replaceDynamicLocator(selectProductPMP, String.valueOf(intanceNumber)), 10, Direction.UP, 5).click();
		Assert.assertTrue(getElement(driver, productTitle, 20).isDisplayed(), "Product descrition page should be displayed.");
	}
}
