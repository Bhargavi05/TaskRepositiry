package com.ebay.Repository;

public interface Locator_HomePage {
	
	String logo = "//*[@resource-id='com.ebay.mobile:id/logo']";
	String menu = "//*[@resource-id='com.ebay.mobile:id/home']";
	String searchBox = "//*[@resource-id='com.ebay.mobile:id/search_box']";
	String searchTextbox = "//*[@resource-id='com.ebay.mobile:id/search_src_text']";
	String searchedText = "//*[@resource-id='com.ebay.mobile:id/text']";
	String noResults = "//*[@resource-id='com.ebay.mobile:id/textview_message_title']";

}
