package com.cleartrip.test;

import java.text.ParseException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cleartrip.base.CleartripBase;
import com.cleartrip.page.HomePage;

public class ClearTripTest extends CleartripBase {
	HomePage hmPage=null;

	@BeforeTest
	public void initialize() {
		setUp();
	}
	

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
	
	@Test
	public void roundTrip() throws InterruptedException, ParseException {
		hmPage= new HomePage(driver);
		String departDate=prop.getProperty("depDate");
		String returnDate=prop.getProperty("retDate");
		
		hmPage.selectFlightOption();
		hmPage.selectRoundTrip();
		hmPage.enterFromAndToCities();
		hmPage.selectDepartReturnDate(departDate,returnDate);
		
	}
	

}
