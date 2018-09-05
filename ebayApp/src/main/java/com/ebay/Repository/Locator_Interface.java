package com.ebay.Repository;

public interface Locator_Interface {
	// Home page elements
	String logo = "//*[@resource-id='com.ebay.mobile:id/logo']";
	String menu = "//*[@resource-id='com.ebay.mobile:id/home']";
	String signIn_Menu = "//*[contains(@content-desc,	'Sign in')]";
	String categories = "//*[@text='CATEGORIES']";
	String searchBox = "//*[@resource-id='com.ebay.mobile:id/search_box']";
	String searchTextbox = "//*[@resource-id='com.ebay.mobile:id/search_src_text']";
	String searchedText = "//*[@resource-id='com.ebay.mobile:id/text']";
	String noResults = "//*[@resource-id='com.ebay.mobile:id/textview_message_title']";

	// Sign-In page elements
	String username = "//*[@text='Email or username']";
	String password = "//*[@text='Password']";
	String signIn_button = "//*[@text='SIGN IN']";
	String maybe_button = "//*[contains(@text,'MAYBE')]";

	// Categories page
	String last_Catg = "//*[@text='Everything Else']";

}
