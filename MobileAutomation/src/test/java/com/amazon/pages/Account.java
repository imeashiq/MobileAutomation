package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.reusable.components.TestDataReader;
import com.reusable.components.WebElementBaseClass;

import io.qameta.allure.Step;

public class Account extends WebElementBaseClass {

	TestDataReader testData;

	// Contructor to create object for TestData Reader
	public Account() {
		testData = new TestDataReader();
	}

	// Locators used in this page
	public static String emailIDTxt = "ID:ap_email_login";
	public static String continueBtn = "XPATH://*[@resource-id='continue']";
	public static String passwordTxt = "ID:ap_password";
	public static String signInBtn = "ID:signInSubmit";
	public static String orderTitle = "XPATH://*[@text='Orders']";
	public static String amazonLogo = "ID:com.amazon.mShop.android.shopping:id/chrome_action_bar_home_logo";

	/*
	 * SignIn an account with given user type.
	 */
	@Step("SignIn an account with given user type")
	public void loginAccount(WebDriver driver, String userType) {
		String[] userDetails = testData.userDataReader(userType);
		String email = userDetails[0];
		String password = userDetails[1];
		getElement(driver, emailIDTxt, 10).sendKeys(email);
		getElement(driver, continueBtn, 10).click();
		getElement(driver, passwordTxt, 10).sendKeys(password);
		getElement(driver, signInBtn, 10).click();
		Assert.assertTrue(getElement(driver, orderTitle, 15).isDisplayed(), "User is not logged in successfully.");
	}

	/*
	 * Navigate to homepage from account page
	 */
	@Step("Navigate to homepage from account page")
	public void navigateToHomePage(WebDriver driver) {
		getElement(driver, amazonLogo, 10).click();
	}
}
