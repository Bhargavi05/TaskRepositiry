package com.ebay.Repository;

public interface Locator_Interface {
	String logo="//*[@resource-id='com.ebay.mobile:id/logo']";
	String menu="//*[@resource-id='com.ebay.mobile:id/home']";
	String signIn_Menu="//*[contains(@content-desc,	'Sign in')]";
//	Sign In page elements
	String username="//*[@text='Email or username']";
	String password="//*[@text='Password']";
	String signIn_button="//*[@text='SIGN IN']";
}
