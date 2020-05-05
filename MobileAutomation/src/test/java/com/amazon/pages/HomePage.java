package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.reusable.components.TestDataReader;
import com.reusable.components.WebElementBaseClass;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.qameta.allure.Step;

public class HomePage extends WebElementBaseClass {
	TestDataReader testData;
	WebDriver driver;
	
	// Contructor to create object for TestData Reader and get WebDriver
	public HomePage(WebDriver driver) {
		testData = new TestDataReader();
		this.driver = driver;
	}

	// Locators used in this page
	public static String openHamburgermenu = "XPATH://*[@resource-id='com.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon']";
	public static String closeHamburgermenu = "XPATH://*[@resource-id='com.amazon.mShop.android.shopping:id/wnd_header_close_button']";
	public static String searchBar = "XPATH://*[@resource-id='com.amazon.mShop.android.shopping:id/rs_search_src_text']";
	public static String yourAccount = "XPATH://*[@text='Your Account']";
	public static String welcomeTitle = "XPATH://*[@text='Welcome']";

	/*
	 * To open HamburgerMenu from HomePage
	 */
	@Step("Open Hamburger Menu")
	public void openHamburgerMenu() {
		getElement(driver, openHamburgermenu, 15).click();
		Assert.assertTrue(getElement(driver, closeHamburgermenu, 15).isDisplayed(), "Hamburger Menu should be opened.");
	}

	/*
	 * Navigate to account from HamburgerMenu
	 */
	@Step("Navigate to account from Hamburger menu")
	public void navigateToAccount() {
		getElement(driver, yourAccount, 10).click();
		Assert.assertTrue(getElement(driver, welcomeTitle, 15).isDisplayed(), "Account page should be opened.");
	}

	/*
	 * Search for a product with given keyword
	 */
	@Step("Search for product with keyword")
	public void searchProduct(String productType) {
		String searchKeyword = testData.productDataReader(productType);
		getElement(driver, searchBar, 10).sendKeys(searchKeyword);
		pressKeyEvent(driver, AndroidKey.ENTER);
	}
}
