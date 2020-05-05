package com.amazon.testScenarios.checkout;

import org.testng.annotations.Test;

import com.amazon.pages.Account;
import com.amazon.pages.Cart;
import com.amazon.pages.Checkout;
import com.amazon.pages.HomePage;
import com.amazon.pages.PDP;
import com.amazon.pages.PMP;
import com.reusable.components.DriverBaseClass;

import io.qameta.allure.Feature;

public class Checkout_Place_an_order_by_searching_a_product extends DriverBaseClass{

	// TestData used for this test
	String searchKeyword = "searchTV";
	String userType = "LoggedInUser";
	String paymentType = "Visa";
	String addressType = "FremontAddress";

	@Test(testName = "Search for a given product and place an order")
	@Feature("Checkout")
	public void testMethod() {

		// Create object that are being used in this test scenario
		HomePage homePage = new HomePage();
		Account account = new Account();
		PMP pmp = new PMP();
		PDP pdp = new PDP();
		Cart cart = new Cart();
		Checkout checkout = new Checkout();

		// Open HamburgerMenu
		homePage.openHamburgerMenu();
		// Navigate to Account
		homePage.navigateToAccount();
		// Login with emailID
		account.loginAccount(userType);
		// Navigate back to home
		account.navigateToHomePage();
		// Search for product with keyword
		homePage.searchProduct(searchKeyword);
		// Select a product from search result
		pmp.selectProductFromSearchResult(3);
		// Get item details and add it to cart
		String[] productDetails = pdp.addItemToCart();
		// Navigate to cart & verify the details
		pdp.navigateToCart();
		cart.verifyProductDetails(productDetails);
		// Proceed to checkout
		cart.proceedToCheckout();
		// Enter the shipping Address
		checkout.enterAddress(addressType);
		// Select default shipping method
		checkout.selectStandardShipping();
		// Enter the payment details
		checkout.enterPayment(paymentType);
		// Verify product details in checkout and place order
		checkout.verifyProductDetails(productDetails);
		checkout.placeOrder();
	}
}
