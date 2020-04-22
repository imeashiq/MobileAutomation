package com.amazon.testScenarios.checkout;

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

	@Test(testName = "Search for a given product and place an order")
	public void testMethod() {
		// Create object that are being used in this test scenario
		HomePage homePage = new HomePage();
		Account account = new Account();
		PMP pmp = new PMP();
		PDP pdp = new PDP();
		Cart cart = new Cart();
		Checkout checkout = new Checkout();

		// Open HamburgerMenu
		homePage.openHamburgerMenu(getDriver());
		// Navigate to Account
		homePage.navigateToAccount(getDriver());
		// Login with emailID
		account.loginAccount(getDriver(), userType);
		// Navigate back to home
		account.navigateToHomePage(getDriver());
		// Search for product with keyword
		homePage.searchProduct(getDriver(), searchKeyword);
		// Select a product from search result
		pmp.selectProductFromSearchResult(getDriver(), 3);
		// Get item details and add it to cart
		String[] productDetails = pdp.addItemToCart(getDriver());
		// Navigate to cart & verify the details
		pdp.navigateToCart(getDriver());
		cart.verifyProductDetails(getDriver(), productDetails);
		// Proceed to checkout
		cart.proceedToCheckout(getDriver());
		// Enter the shipping Address
		checkout.enterAddress(getDriver(), addressType);
		// Select default shipping method
		checkout.selectStandardShipping(getDriver());
		// Enter the payment details
		checkout.enterPayment(getDriver(), paymentType);
		// Verify product details in checkout and place order
		checkout.verifyProductDetails(getDriver(), productDetails);
		checkout.placeOrder(getDriver());
	}
}
