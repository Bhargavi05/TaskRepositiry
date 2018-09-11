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

//Class description: 
//*****This class contains all testcases related to search feature *****\\

public class Search implements Locator_Interface {

	WebDriver driver;
	String platform;
	SoftAssert s_assert = new SoftAssert();
	SetupFile integratoObj = new SetupFile();
	GenericMethods generic;

	// To read test data
	@DataProvider
	public Object[][] FormsData() throws Exception {

		return com.ebay.Generic.Functions.ExcelDataProvider
				.dataProvider(System.getProperty("user.dir") + "/TestData/ebayTestcases.xlsx", "Search", "Y");

	}

	@Parameters({ "platformName", "platformVersion", "deviceName", "deviceOrientation", "automationName", "delay",
			"unicodeKeyboard", "resetKeyboard" })

	@BeforeMethod
	public void Setup(String platformName, String platformVersion, String deviceName, String deviceOrientation,
			String automationName, int delay, String unicodeKeyboard, String resetKeyboard)
			throws Exception {

		driver = integratoObj.mobileDriver(platformName, platformVersion, deviceName, deviceOrientation, automationName,
				delay, unicodeKeyboard, resetKeyboard);

		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		generic = new GenericMethods(driver);
		WebDriverRunner.setWebDriver(driver);

	}

	@Test(dataProvider = "FormsData")
	public void search(Hashtable<String, String> data) throws InterruptedException, Exception {

		// To get tc name from excel
		String TestcaseName = data.get("TestCase");

		// To verify app launch
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
			// Test case:To search an item
			if (data.get("VerificationKey").equalsIgnoreCase("Search")) {
				System.out.println(TestcaseName);
				driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
				// Method to search particular item based on test data
				GenericMethods.searchItem(data.get("InputValue"));
				driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
				if (GenericMethods.isObjectDisplayed(noResults)) {
					Reporter.log(TestcaseName + "Passed", true);
					GenericMethods.toCaptureScreenShot();
				}
				Thread.sleep(1000);
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
