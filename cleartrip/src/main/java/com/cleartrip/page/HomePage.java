package com.cleartrip.page;

import java.text.ParseException;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cleartrip.base.CleartripBase;
import com.cleartrip.util.Util;

public class HomePage extends CleartripBase {
	Select select =null;

	@FindBy(xpath="//a[text()='Flights']")
	public WebElement flightOption;

	@FindBy(xpath="//input[@value='RoundTrip']")
	public WebElement roundTrip;

	@FindBy(xpath="//input[@name='origin']")
	public WebElement fromCity;

	@FindBy(xpath="//input[@name='destination']")
	public WebElement toCity;

	@FindBy(xpath="//input[@id='DepartDate']")
	public WebElement departCalendar;

	@FindBy(xpath="//input[@id='ReturnDate']")
	public WebElement returnCalendar;

	@FindBy(xpath="//select[@id='Adults']")
	public WebElement numOfAdults;

	@FindBy(xpath="//select[@id='Childrens']")
	public WebElement numOfChildren;

	@FindBy(xpath="//select[@id='Infants']")
	public WebElement numOfInfants;

	@FindBy(xpath="//input[@id='SearchBtn']")
	public WebElement searchFilghts;


	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public boolean selectFlightOption() {
		Util.waitForElement(driver,flightOption);
		flightOption.click();
		return flightOption.isDisplayed();
	}

	public boolean selectRoundTrip() {
		Util.waitForElement(driver, roundTrip);
		roundTrip.click();
		return roundTrip.isSelected();
	}

	public Map<String, String> enterFromAndToCities(String fromPla, String toPla) {
		Map<String,String> expCities= new HashMap<String, String>();

		Util.waitForElement(driver,fromCity);
		fromCity.sendKeys(fromPla);
		toCity.sendKeys(toPla);

		String fromPlace=fromCity.getAttribute("value");
		String toPlace=toCity.getAttribute("value");

		expCities.put("depCity", fromPlace);
		expCities.put("toCity", toPlace);
		return expCities;
	}

	public void selectDepartReturnDate(String fromDate, String toDate) throws ParseException  {
		Util.waitForElement(driver,departCalendar);
		departCalendar.click();	
		departCalendar.sendKeys(fromDate);
		Util.waitForElement(driver,returnCalendar);
		returnCalendar.click();
		returnCalendar.sendKeys(toDate);
	}

	public Map<String,String> selectAdultsChilderInfants(String noAdults, String noChildren, String noInfants) {
		Map<String,String> passenger= new HashMap<String, String>();
		select = new Select(numOfAdults);
		select.selectByVisibleText(noAdults);
		
		select = new Select(numOfChildren);
		select.selectByVisibleText(noChildren);
		
		select = new Select(numOfInfants);
		select.selectByVisibleText(noInfants);
		
		String noAdultsPassgr=numOfAdults.getAttribute("value");
		String noChildrenPassgr=numOfChildren.getAttribute("value");
		String noInfantsPassgr=numOfInfants.getAttribute("value");
		
		passenger.put("noAdultsPassgr", noAdultsPassgr);
		passenger.put("noChildrenPassgr", noChildrenPassgr);
		passenger.put("noInfantsPassgr", noInfantsPassgr);
		
		return passenger;
	}
	
	public void searchFlights() {
		Util.waitForElement(driver,searchFilghts);
		searchFilghts.click();
	}




}

