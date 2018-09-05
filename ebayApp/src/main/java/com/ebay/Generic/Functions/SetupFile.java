package com.ebay.Generic.Functions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

//Class description: 
//*****This class is for launching app using appium with all required capabilities *****\\

public class SetupFile {
	DesiredCapabilities caps;

	public static ThreadLocal<AppiumDriver<WebElement>> driver = new ThreadLocal<AppiumDriver<WebElement>>();
	public AppiumDriver<WebElement> getWebDriver() {
		return driver.get();
	}
	public WebDriver mobileDriver(String platformName, String platformVersion, String deviceName,
			String deviceOrientation,String automationName, int delay,  String resetKeyboard, String unicodeKeyboard)
			throws MalformedURLException {

		caps = new DesiredCapabilities();
		caps.setCapability("appium-version", "1.6.3");
		caps.setCapability("platformName", platformName);
		caps.setCapability("platformVersion", platformVersion);
		caps.setCapability("deviceOrientation", deviceOrientation);
		caps.setCapability("deviceName", deviceName);
		caps.setCapability("automationName", automationName);
		caps.setCapability(MobileCapabilityType.FULL_RESET,true);

		if (platformName.equalsIgnoreCase("Android")) {
			// Code to hide keyboard
			caps.setCapability("unicodeKeyboard", unicodeKeyboard);
			caps.setCapability("resetKeyboard", resetKeyboard);
			caps.setCapability("app", "C:\\Users\\bhargavi\\Desktop\\eBay.apk");
			driver.set(new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps));
		}

		// returns the desired driver object
		return driver.get();
	}
}
