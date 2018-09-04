package com.ebay.Repository;

import org.openqa.selenium.WebDriver;

public abstract class Page {

	static protected WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
	}

}
