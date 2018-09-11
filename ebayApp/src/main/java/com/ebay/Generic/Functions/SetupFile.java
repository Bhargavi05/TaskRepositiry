package com.ebay.Generic.Functions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

//Class description: 
//*****This class is for launching app using appium with all required capabilities *****\\

public class SetupFile {
	DesiredCapabilities caps;
	static String NodePath = "C:/Program Files/nodejs/node.exe";
	static String AppiumMainJS_Path = "C:/Users/bhargavi/AppData/Local/appium-desktop/app-1.5.0/resources/app/node_modules/appium/build/lib/main.js";
	static AppiumDriverLocalService service;

	public static ThreadLocal<AppiumDriver<WebElement>> driver = new ThreadLocal<AppiumDriver<WebElement>>();

	public AppiumDriver<WebElement> getWebDriver() {
		return driver.get();
	}

	public WebDriver mobileDriver(String platformName, String platformVersion, String deviceName,
			String deviceOrientation, String automationName, int delay, String resetKeyboard, String unicodeKeyboard)
			throws MalformedURLException, Exception {

		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File(NodePath))
						.withIPAddress("127.0.0.1").usingPort(4723).withAppiumJS(new File(AppiumMainJS_Path)));
		System.out.println("About to start");

		// service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		Thread.sleep(10000);
		System.out.println("Is Server running "+ service.isRunning());

		caps = new DesiredCapabilities();
		caps.setCapability("appium-version", "1.7.2");
		caps.setCapability("platformName", platformName);
		caps.setCapability("platformVersion", platformVersion);
		caps.setCapability("deviceOrientation", deviceOrientation);
		caps.setCapability("deviceName", deviceName);
		caps.setCapability("automationName", automationName);
		caps.setCapability(MobileCapabilityType.FULL_RESET, true);

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
