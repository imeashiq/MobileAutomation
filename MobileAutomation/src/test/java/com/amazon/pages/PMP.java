package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.reusable.components.TestDataReader;
import com.reusable.components.WebElementBaseClass;

import io.qameta.allure.Step;

public class PMP extends WebElementBaseClass {
	TestDataReader testData;
	WebDriver driver;

	// Locators used in this page
	public static String selectProductPMP = "XPATH://*[@instance='<<<>>>']";
	public static String productTitle = "ID:title_feature_div";

	// Contructor to create object for TestData Reader and get WebDriver
	public PMP() {
		testData = new TestDataReader();
		this.driver = super.getDriver();
	}

	/*
	 * Scroll and select a product from the search result dynamically
	 */
	@Step("Scroll and select a product from the search result")
	public void selectProductFromSearchResult(int itemToBeSelected) {
		int intanceNumber = 39 + (itemToBeSelected * 8);
		scrollAndSearchElement(driver, replaceDynamicLocator(selectProductPMP, String.valueOf(intanceNumber)), 10,
				Direction.UP, 5).click();
		Assert.assertTrue(getElement(driver, productTitle, 20).isDisplayed(),
				"Product descrition page should be displayed.");
	}
}
