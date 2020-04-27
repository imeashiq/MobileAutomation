package com.amazon.testScenarios.checkout;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.amazon.pages.Account;
import com.amazon.pages.Cart;
import com.amazon.pages.Checkout;
import com.amazon.pages.HomePage;
import com.amazon.pages.PDP;
import com.amazon.pages.PMP;
import com.reusable.components.DriverBaseClass;

public class Checkout_Place_an_order_by_searching_a_product extends DriverBaseClass {

	// TestData used for this test
	String searchKeyword = "searchTV";
	String userType = "LoggedInUser";
	String paymentType = "Visa";
	String addressType = "FremontAddress";
	WebDriver driver;

	@Test(testName = "Search for a given product and place an order")
	public void testMethod() {

		// Get the driver instance from the BaseWebDriver class
		driver = getDriver();

		// Create object that are being used in this test scenario
		HomePage homePage = new HomePage();
		Account account = new Account();
		PMP pmp = new PMP();
		PDP pdp = new PDP();
		Cart cart = new Cart();
		Checkout checkout = new Checkout();

		// Open HamburgerMenu
		homePage.openHamburgerMenu(driver);
		// Navigate to Account
		homePage.navigateToAccount(driver);
		// Login with emailID
		account.loginAccount(driver, userType);
		// Navigate back to home
		account.navigateToHomePage(driver);
		// Search for product with keyword
		homePage.searchProduct(driver, searchKeyword);
		// Select a product from search result
		pmp.selectProductFromSearchResult(driver, 3);
		// Get item details and add it to cart
		String[] productDetails = pdp.addItemToCart(driver);
		// Navigate to cart & verify the details
		pdp.navigateToCart(driver);
		cart.verifyProductDetails(driver, productDetails);
		// Proceed to checkout
		cart.proceedToCheckout(driver);
		// Enter the shipping Address
		checkout.enterAddress(driver, addressType);
		// Select default shipping method
		checkout.selectStandardShipping(driver);
		// Enter the payment details
		checkout.enterPayment(driver, paymentType);
		// Verify product details in checkout and place order
		checkout.verifyProductDetails(driver, productDetails);
		checkout.placeOrder(driver);
	}
}
