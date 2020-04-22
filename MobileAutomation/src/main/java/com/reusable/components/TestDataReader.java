package com.reusable.components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class TestDataReader {
	static String ProductData;
	static String UserData;
	static String PaymentData;
	static String AddressData;
	Object document;
	
	public TestDataReader() {
		try {
			ProductData = new String(Files.readAllBytes(Paths.get("Resources/ProductDetails.json")));
			UserData = new String(Files.readAllBytes(Paths.get("Resources/UserDetails.json")));
			PaymentData = new String(Files.readAllBytes(Paths.get("Resources/Payment.json")));
			AddressData = new String(Files.readAllBytes(Paths.get("Resources/Address.json")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String productDataReader(String productType) {
		document = Configuration.defaultConfiguration().jsonProvider().parse(ProductData);
		String testData = String.valueOf(JsonPath.read(document, "$.productDetails[?(@.productType=='" + productType + "')].searchTerm")).replace("[", "").replace("]", "").replace("\"", "");
		return testData;
	}
	
	public String[] userDataReader(String userType) {
		document = Configuration.defaultConfiguration().jsonProvider().parse(UserData);
		String emailID = String.valueOf(JsonPath.read(document, "$.userDetails[?(@.userType=='" + userType + "')].emailID")).replace("[", "").replace("]", "").replace("\"", "");
		String password = String.valueOf(JsonPath.read(document, "$.userDetails[?(@.userType=='" + userType + "')].password")).replace("[", "").replace("]", "").replace("\"", "");
		return new String[] {emailID, password};
	}
	
	public String[] paymentDataReader(String paymentType) {
		document = Configuration.defaultConfiguration().jsonProvider().parse(PaymentData);
		String name = String.valueOf(JsonPath.read(document, "$.paymentDetails[?(@.userType=='" + paymentType + "')].name")).replace("[", "").replace("]", "").replace("\"", "");
		String cardNumber = String.valueOf(JsonPath.read(document, "$.paymentDetails[?(@.userType=='" + paymentType + "')].cardNumber")).replace("[", "").replace("]", "").replace("\"", "");
		String expMonth = String.valueOf(JsonPath.read(document, "$.paymentDetails[?(@.userType=='" + paymentType + "')].expMonth")).replace("[", "").replace("]", "").replace("\"", "");
		String expYear = String.valueOf(JsonPath.read(document, "$.paymentDetails[?(@.userType=='" + paymentType + "')].expYear")).replace("[", "").replace("]", "").replace("\"", "");
		return new String[] {name, cardNumber, expMonth, expYear};
	}
	
	public String[] addressDataReader(String addressType) {
		document = Configuration.defaultConfiguration().jsonProvider().parse(AddressData);
		String name = String.valueOf(JsonPath.read(document, "$.addressDetails[?(@.userType=='" + addressType + "')].name")).replace("[", "").replace("]", "").replace("\"", "");
		String street = String.valueOf(JsonPath.read(document, "$.addressDetails[?(@.userType=='" + addressType + "')].street")).replace("[", "").replace("]", "").replace("\"", "");
		String city = String.valueOf(JsonPath.read(document, "$.addressDetails[?(@.userType=='" + addressType + "')].city")).replace("[", "").replace("]", "").replace("\"", "");
		String state = String.valueOf(JsonPath.read(document, "$.addressDetails[?(@.userType=='" + addressType + "')].state")).replace("[", "").replace("]", "").replace("\"", "");
		String zip = String.valueOf(JsonPath.read(document, "$.addressDetails[?(@.userType=='" + addressType + "')].zip")).replace("[", "").replace("]", "").replace("\"", "");
		String phone = String.valueOf(JsonPath.read(document, "$.addressDetails[?(@.userType=='" + addressType + "')].phone")).replace("[", "").replace("]", "").replace("\"", "");
		return new String[] {name, street, city, state, zip, phone};
	}
}
