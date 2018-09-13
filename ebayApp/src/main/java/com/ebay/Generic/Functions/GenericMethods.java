package com.ebay.Generic.Functions;

import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.Reporter;

import com.ebay.Generic.Functions.GenericMethods;
import com.ebay.Repository.Locator_Interface;
import com.ebay.Repository.Page;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

//Class description: 
//*****This class contains generic methods means commons methods for all feature *****\\

public class GenericMethods extends Page implements Locator_Interface {

	// WebDriver driver;
	static Collator coll = Collator.getInstance(Locale.US);
	static MobileDriver mdriver = (MobileDriver) driver;

	public GenericMethods(WebDriver driver) {
		super(driver);
	}

	/*
	 * Method Name: isObjectDisplayed Script Developer: Bhargavi Creation Date:
	 * Sep 4th Purpose: Method to Check object is displayed on UI
	 */
	public static boolean isObjectDisplayed(String xpathofObject) throws Exception {
		String actualString = null;
		boolean matchFlag = true;
		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement clickableButton = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathofObject)));

			if (clickableButton.isDisplayed()) {
				driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
				actualString = clickableButton.getAttribute("text");
				System.out.println("" + actualString + " object is displayed");
				Reporter.log(actualString + " object is displayed", true);
				matchFlag = true;
			}
		} catch (Exception ex) {
			GenericMethods.failTestCase("" + actualString + " object is not displayed");
			matchFlag = false;
		}
		return matchFlag;
	}

	/*
	 * Method Name: clickOnButton Script Developer: Bhargavi Creation Date: Sep
	 * 4th Purpose: Method to click on any button on screen
	 */

	public static void clickOnButton(String locatorName) throws Exception {
		String actualString = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 120);
			WebElement clickableButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorName)));
			actualString = clickableButton.getAttribute("text");
			Reporter.log("Button " + actualString + " was clicked", true);
			clickableButton.click();
		} catch (Exception ex) {
			GenericMethods.failTestCase("Button " + actualString + " was not clicked ");
		}
	}

	/*
	 * Method Name: failTestCase Script Developer: Bhargavi Creation Date: Sep
	 * 4th Purpose: Method to log failures from functions
	 */

	public static void failTestCase(String failMessage) throws Exception {

		Reporter.log(failMessage, true);
		GenericMethods.toCaptureScreenShot();
		Assert.fail(failMessage);
	}

	/*
	 * Method Name: SignIn Script Developer: Bhargavi Creation Date: Sep 4th
	 * Purpose: Method to click on any button on screen
	 */
	public static void signIn(String userName, String pwd) throws Exception {
		String actualString = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 120);
			driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
			// Check for the availability of signin
			GenericMethods.isObjectDisplayed(signIn_Menu);
			// Click on sign in menu item
			GenericMethods.clickOnButton(signIn_Menu);
			driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
			// Enter Username
			GenericMethods.inputTextInField(username, userName);
			Reporter.log("Entered UN", true);
			driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
			// Enter Password
			GenericMethods.inputTextInField(password, pwd);
			driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
			Reporter.log("Entered Pwd", true);
			// Click on Signin Button
			GenericMethods.clickOnButton(signIn_button);
			driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
			System.out.println("After wait");
			GenericMethods.clickOnButton(maybe_button);
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
			GenericMethods.isObjectDisplayed(logo);
			Reporter.log("Signned In successfully", true);
			GenericMethods.toCaptureScreenShot();
		} catch (Exception ex) {
			GenericMethods.failTestCase("Button " + actualString + " was not clicked ");
		}
	}

	/*
	 * Method Name: inputTextInField Script Developer: Bhargavi Creation Date:
	 * Purpose: Input unformatted text into
	 */
	public static void inputTextInField(String locatorName, String InputText) throws Exception {
		WebElement findField = driver.findElement(By.xpath(locatorName));
		try {
			// If field is located, click the field then clear it then enter the
			// text
			if (findField.isDisplayed()) {
				findField.click();
				findField.clear();
				findField.sendKeys(InputText);
				Reporter.log(InputText + " was input into UI");
			}
		} catch (Exception ex) {
			GenericMethods.failTestCase(InputText + " was not input into UI");
		}
	}

	/*
	 * Method Name: appLaunch Script Developer: Bhargavi Creation Date: Sep 4th
	 * Purpose: Method to verify appLaunch
	 */

	public boolean verifyAppLaunch() throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
			// To verify app logo
			GenericMethods.isObjectDisplayed(logo);
			// GenericMethods.toCaptureScreenShot();
		} catch (Exception ex) {
			GenericMethods.failTestCase("Tc fails as app not Launched");
		}
		return false;
	}

	/*
	 * Method Name: ScrollToBottomOfScreen Script Developer: Bhargavi Creation
	 * Date: Sep 4th Purpose: Method to scroll to the bottom of the screen
	 */
	public static void scrollToBottomOfScreen() throws InterruptedException {

		Dimension elementdimension = driver.findElement(By.xpath(baby_Mom_Catg)).getSize();
		int startx = elementdimension.getWidth() * 1;
		int starty = (int) (elementdimension.getHeight() * 10);
		int endy = (int) (elementdimension.getHeight() * 5);
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			Thread.sleep(1000);
			TouchAction action = new TouchAction((MobileDriver) driver);
			action.press(startx, starty).waitAction(1000).moveTo(startx, endy).release().perform();
			Thread.sleep(3000);
		}
	}

	/*
	 * Method Name: searchItem Script Developer: Bhargavi Creation Date: Sep 4th
	 * Purpose: Method to search an item
	 */
	public static void searchItem(String InputText) throws InterruptedException, Exception {
		WebElement findField = driver.findElement(By.xpath(searchBox));
		try {
			if (findField.isDisplayed()) {
				// Click on search text box
				findField.click();
				Reporter.log("Clicked on searchBok", true);
				driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
				// CLear data
				findField.clear();
				Reporter.log("Cleared searchBok", true);
				// Input text
				driver.findElement(By.xpath(searchTextbox)).sendKeys(InputText);
				driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
				Reporter.log(InputText + " was input into UI", true);
				// select required item
				WebElement displayedElement = driver.findElement(By.xpath(searchedText));
				displayedElement.click();
				Reporter.log("Clicked on Mobile Phones", true);
				GenericMethods.toCaptureScreenShot();
			}
		} catch (Exception ex) {
			GenericMethods.failTestCase(InputText + " was not input into UI");
		}
	}
	/*
	 * Method Name: toCaptureScreenShot Script Developer: Bhargavi Creation
	 * Date: Sep 6th Purpose: Method to capture required screenshot
	 */

	// Creating a method getScreenshot and passing two parameters
	public static String getScreenshot() throws Exception {
		// below line is just to append the date format with the screenshot name
		// to avoid duplicate names
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "TestsScreenshots" under src
		// folder
		String location = System.getProperty("user.dir") + "\\test-output\\screenshot\\" + "SS" + dateName + ".png";
		// File finalDestination = new File(destination);
		File screenshotLocation = new File(location);
		FileUtils.copyFile(source, screenshotLocation);
		return location;
	}

	public static void toCaptureScreenShot() {
//		Object currentClass = arg0.getInstance();
		// WebDriver driver = ((BrowserSetup) currentClass).getDriver();
		// String name = arg0.getName();
		// System.out.println(name);
		try {
			String screenshotPath = getScreenshot();
			System.out.println("Screenshot taken");
			String path = "<img src=\"file://" + screenshotPath + "\" alt=\"\"/>";
			System.out.println(screenshotPath + " and path - " + path);
			Reporter.log("Capcher screenshot path is " + path);
		} catch (Exception e) {
			System.out.println("Exception while takescreenshot " + e.getMessage());
		}

	}

}