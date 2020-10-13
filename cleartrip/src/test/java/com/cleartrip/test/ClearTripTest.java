package com.cleartrip.test;

import java.text.ParseException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cleartrip.base.CleartripBase;
import com.cleartrip.page.HomePage;
import com.cleartrip.page.ItineraryPage;
import com.cleartrip.page.SearchFlightsResult;
import com.cleartrip.util.Util;

public class ClearTripTest extends CleartripBase {
	HomePage hmPage=null;
	SearchFlightsResult serachflight=null;
	ItineraryPage itr=null;

	@BeforeTest
	public void initialize() {
		setUp();
	}


	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

	@Test(priority=1, description="Select flight option of LHS of application")
	public void selectFlightOption() throws ParseException {
		hmPage= new HomePage(driver);
		boolean isDisplayed=hmPage.selectFlightOption();
		Assert.assertEquals(isDisplayed, true, "Flight option is not displayed");
	}

	@Test(priority=2,description="To check radio button for round trip is selected or not")
	public void isRoundTripSelected() {
		hmPage= new HomePage(driver);
		boolean isClicked=hmPage.selectRoundTrip();
		Assert.assertEquals(isClicked, true, "Reound Trip radio button is not clicked");
	}

	@Test(priority=3,description="Enter Depart and Return cities and validate same displayed on web page")
	public void enterDepRetCities() {
		hmPage= new HomePage(driver);

		String fromCity=prop.getProperty("fromCity");
		String toCity=prop.getProperty("toCity");

		Util.cityCompare(fromCity,toCity);

		Map<String, String> map=hmPage.enterFromAndToCities(fromCity,toCity);
		Assert.assertEquals(fromCity, map.get("depCity"), "Entered depart city name is not same as displayed city name");
		Assert.assertEquals(toCity, map.get("toCity"), "Entered return city name is not same as displayed city name");
	}

	@Test(priority=4,description="Enter depart and return dates and validate enterd dates are same as displayed on web page")
	public void enterDepRetDates() throws ParseException {
		hmPage= new HomePage(driver);
		String departDate=prop.getProperty("depDate");
		String returnDate=prop.getProperty("retDate");
		Util.dateValidator(departDate, returnDate);
		hmPage.selectDepartReturnDate(departDate,returnDate);
	}

	@Test(priority=5,description="Select the number of passangers and validate displayed passenger values are same as entered")
	public void selectNumOfPassengers() {
		hmPage= new HomePage(driver);
		String noAdults=prop.getProperty("noAdults");
		String noChildren=prop.getProperty("noChildren");
		String noInfants=prop.getProperty("noInfants");

		Map<String,String> passengers=hmPage.selectAdultsChilderInfants(noAdults, noChildren, noInfants);
		Assert.assertEquals(noAdults, passengers.get("noAdultsPassgr"), "Entered depart city name is not same as displayed city name");
		Assert.assertEquals(noChildren, passengers.get("noChildrenPassgr"), "Entered depart city name is not same as displayed city name");
		Assert.assertEquals(noInfants, passengers.get("noInfantsPassgr"), "Entered depart city name is not same as displayed city name");

	}
	@Test(priority=6,description="Click on search flight button to search all flights b/w entered cities")
	public void clickSearchFlightsButton() {
		hmPage= new HomePage(driver);
		hmPage.searchFlights();
	}

	@Test(priority=7,description="Book depart and return flight tickets")
	public void bookFlights() {
		serachflight = new SearchFlightsResult(driver);
		serachflight.selectDepartAndReturnFlights();
	}
	
	
	@Test(priority=8,description="Depart and return cities are displayed on itinerary page")
	public void DepartRetrunCitiesDisplayed() {
		itr = new ItineraryPage(driver);
		boolean isCityNameDisplayed=itr.isDepartRetrunCitiesDisplayed();
		Assert.assertEquals(isCityNameDisplayed, true, "Depart and return cities are not displayed on itinerary page");

	}
	
	@Test(priority=9,description="Book depart and return flight tickets")
	public void enterPassengerDetails() {
		itr = new ItineraryPage(driver);
		String emailId=prop.getProperty("email");
		itr.clicnOnContinue(emailId);
	}
	@Test(priority=10,description="To check payment page is displayed or not")
	public void checkPaymentPageDisplayed() throws InterruptedException {
		itr = new ItineraryPage(driver);
		boolean paymentDisplayed=itr.passengerDetails();
		Assert.assertEquals(paymentDisplayed, true, "Payment page is not displayed on itinerary page");
	}
}
