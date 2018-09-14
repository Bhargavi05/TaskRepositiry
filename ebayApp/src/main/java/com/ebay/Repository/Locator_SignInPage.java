package com.ebay.Repository;

public interface Locator_SignInPage {
	
	String signIn_Menu = "//*[contains(@content-desc,	'Sign in')]";
	String username = "//*[@text='Email or username']";
	String password = "//*[@text='Password']";
	String signIn_button = "//*[@text='SIGN IN']";
	String maybe_button = "//*[contains(@text,'MAYBE')]";

}
