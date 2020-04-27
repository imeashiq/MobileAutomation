package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.reusable.components.TestDataReader;
import com.reusable.components.WebElementBaseClass;

import io.qameta.allure.Step;

public class Checkout extends WebElementBaseClass {
	TestDataReader testData;

	// Constructor to create object for TestData Reader
	public Checkout() {
		testData = new TestDataReader();
	}

	// Locators used in this page
	// Address
	public static String addressFirstName = "ID:enterAddressFullName";
	public static String addressStreet = "ID:enterAddressAddressLine1";
	public static String addressCity = "ID:enterAddressCity";
	public static String addressState = "ID:enterAddressStateOrRegion";
	public static String addressZIP = "ID:enterAddressPostalCode";
	public static String addressPhoneNumber = "ID:enterAddressPhoneNumber";
	public static String continueBtn = "XPATH://android.widget.Button[@text='Continue']";
	// Select Delivery
	public static String standardShipping = "ID:order_0_ShippingSpeed_sss-us";
	// Payment
	public static String nameOnCardTxt = "ID:pp-mduU6A-68";
	public static String cardNumberTxt = "ID:pp-mduU6A-69";
	public static String expMonthTxt = "ID:pp-mduU6A-74";
	public static String expYearTxt = "ID:pp-mduU6A-75";
	public static String addYourCard = "ID:pp-mduU6A-77";
	// Place order
	public static String placeOrder = "XPATH://android.widget.Button[@text='Place your order']";
	// Dynamic Xpath
	public static String genericXpath = "XPATH://*[@text=<<<>>>]";

	/*
	 * Method to enter the shipping Address details in checkout page
	 */
	@Step("Enter the shipping Address details in checkout page")
	public void enterAddress(WebDriver driver, String addressType) {
		String address[] = testData.addressDataReader(addressType);
		String firstName = address[0];
		String street = address[1];
		String city = address[2];
		String state = address[3];
		String zip = address[4];
		String phoneNumber = address[5];
		getElement(driver, addressFirstName, 15).sendKeys(firstName);
		getElement(driver, addressStreet, 5).sendKeys(street);
		getElement(driver, addressCity, 5).sendKeys(city);
		getElement(driver, addressState, 5).sendKeys(state);
		getElement(driver, addressZIP, 5).sendKeys(zip);
		scrollAndSearchElement(driver, addressPhoneNumber, 10, Direction.UP, 1).sendKeys(phoneNumber);
		scrollAndSearchElement(driver, continueBtn, 10, Direction.UP, 5).click();
	}

	/*
	 * Method to select standard shipping
	 */
	@Step("Select standard shipping in checkout page")
	public void selectStandardShipping(WebDriver driver) {
		getElement(driver, standardShipping, 15).click();
		scrollAndSearchElement(driver, continueBtn, 10, Direction.UP, 5).click();
	}

	/*
	 * Method to enter payment details to order
	 */
	@Step("Enter payment details in checkout page")
	public void enterPayment(WebDriver driver, String paymentType) {
		String[] paymentDetails = testData.paymentDataReader(paymentType);
		String name = paymentDetails[0];
		String cardNumber = paymentDetails[1];
		String expMonth = paymentDetails[2];
		String expYear = paymentDetails[3];

		getElement(driver, nameOnCardTxt, 15).sendKeys(name);
		getElement(driver, cardNumberTxt, 5).sendKeys(cardNumber);
		getElement(driver, expMonthTxt, 5).click();
		getElement(driver, replaceDynamicLocator(genericXpath, expMonth), 5).click();
		getElement(driver, expYearTxt, 5).click();
		getElement(driver, replaceDynamicLocator(genericXpath, expYear), 5).click();
		getElement(driver, addYourCard, 5).click();
		scrollAndSearchElement(driver, continueBtn, 10, Direction.UP, 5).click();
	}

	/*
	 * Method to verify product details in Checkout page
	 */
	@Step("Verify product details in checkout page")
	public void verifyProductDetails(WebDriver driver, String[] productDetails) {
		String productTitle = productDetails[0];
		String productPrice = productDetails[1];
		WebElement productDetail = scrollAndSearchElement(driver, replaceDynamicLocator(genericXpath, productTitle), 15,
				Direction.UP, 10);
		Assert.assertTrue(productDetail.isDisplayed(), "Product Title mismatches");

		productDetail = scrollAndSearchElement(driver, replaceDynamicLocator(genericXpath, productPrice), 5,
				Direction.UP, 2);
		Assert.assertTrue(productDetail.isDisplayed(), "Product Price mismatches");
	}

	/*
	 * Method to place an order
	 */
	@Step("Place an order")
	public void placeOrder(WebDriver driver) {
		scrollAndSearchElement(driver, placeOrder, 15, Direction.UP, 15).click();
	}
}
