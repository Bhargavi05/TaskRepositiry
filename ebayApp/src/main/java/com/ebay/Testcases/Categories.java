package com.ebay.Testcases;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.codeborne.selenide.WebDriverRunner;
import com.ebay.Repository.Locator_Interface;
import com.ebay.Generic.Functions.GenericMethods;
import com.ebay.Generic.Functions.SetupFile;

public class Categories implements Locator_Interface {

	WebDriver driver;
	String platform;
	SoftAssert s_assert = new SoftAssert();
	SetupFile integratoObj = new SetupFile();
	GenericMethods generic;

	// To read test data
	@DataProvider
	public Object[][] FormsData() throws Exception {

		return com.ebay.Generic.Functions.ExcelDataProvider.dataProvider(
				System.getProperty("user.dir") + "/TestData/ebayTestcases_Catg.xlsx", "Sheet1", "Y");

	}

	@Parameters({ "platformName", "platformVersion", "deviceName", "deviceOrientation", "automationName", "delay",
			"unicodeKeyboard", "resetKeyboard" })

	@BeforeMethod
	public void Setup(String platformName, String platformVersion, String deviceName, String deviceOrientation,
			String automationName, int delay, String unicodeKeyboard, String resetKeyboard)
			throws MalformedURLException, InterruptedException {

		driver = integratoObj.mobileDriver(platformName, platformVersion, deviceName, deviceOrientation, automationName,
				delay, unicodeKeyboard, resetKeyboard);

		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		generic = new GenericMethods(driver);
		WebDriverRunner.setWebDriver(driver);

	}

	@Test(dataProvider = "FormsData")
	public void verifyCategories(Hashtable<String, String> data) throws InterruptedException, Exception {

		// To get tc name from excel
		String TestcaseName = data.get("TestCase");

		try {

			boolean applaunchFlag = generic.verifyAppLaunch();
			if (applaunchFlag) {
				System.out.println("App Launched");
			}
		} catch (Exception e) {
			e.printStackTrace();
			GenericMethods.failTestCase("App not launched");
		}
		try {
			// Test case:Click on Categories and scroll down till end
			if (data.get("VerificationKey").equalsIgnoreCase("Categories")) {
				System.out.println(TestcaseName);
				driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
				GenericMethods.clickOnButton(categories);
				// To scroll down till end
				GenericMethods.ScrollToBottomOfScreen();
				if (GenericMethods.isObjectDisplayed(last_Catg)) {
					Reporter.log(TestcaseName + "Passed", true);
				}
			}
		} catch (Exception e) {
			GenericMethods.failTestCase(TestcaseName + "failed");
		}
	}

	@AfterClass
	public void TearDown() {
		driver.quit();

	}

}
