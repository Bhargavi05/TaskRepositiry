package com.ebay.Repository;

public interface Locator_Interface {
//	Home page elements
	String logo="//*[@resource-id='com.ebay.mobile:id/logo']";
	String menu="//*[@resource-id='com.ebay.mobile:id/home']";
	String signIn_Menu="//*[contains(@content-desc,	'Sign in')]";
	String categories="//*[@text='CATEGORIES']";
	
	
//	Sign-In page elements
	String username="//*[@text='Email or username']";
	String password="//*[@text='Password']";
	String signIn_button="//*[@text='SIGN IN']";
	String maybe_button="//*[contains(@text,'MAYBE')]";
	
//	Categories page
	String last_Catg="//*[@text='Everything Else']";
	
}

