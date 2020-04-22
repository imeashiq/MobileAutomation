package com.reusable.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverBaseClass {
	private ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();;
	WebDriver driver;
	String deviceName;
	String deviceID;
	String platformVersion;

	/**
	 * Method is to load details from config property File And to create a Android
	 * driver
	 */
	@BeforeMethod
	public void createDriver() {
		// Load the details from config file
		loadDetails();
		try {
			// Capabilities to create Android Driver
			DesiredCapabilities capability = new DesiredCapabilities();
			capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator1");
			capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			capability.setCapability(MobileCapabilityType.UDID, deviceID);
			capability.setCapability("noReset", true);
			capability.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
			capability.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
			capability.setCapability("appPackage", "com.amazon.mShop.android.shopping");

			// Creating driver with above capabilities using default Appium server
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capability);
			
			setDriver(driver);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Method is to set the driver to Thread after creation
	 */
	public void setDriver(WebDriver driver) {
		this.tlDriver.set(driver);
	}

	/*
	 * Method returns the driver
	 */
	public WebDriver getDriver() {
		return this.tlDriver.get();
	}

	/*
	 * Method to read properties from config file
	 */
	public void loadDetails() {
		InputStream file;
		Properties reader = new Properties();
		try {
			file = new FileInputStream("Resources\\config.properties");
			reader.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.deviceID = reader.getProperty("DeviceID");
		this.deviceName = reader.getProperty("DeviceName");
		this.platformVersion = reader.getProperty("PlatformVersion");
	}
	
	/*
	 * Close the driver after the completion of a test scenario
	 */
	@AfterMethod
	public void closeDriver() {
		getDriver().close();
	}

}
