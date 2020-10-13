package com.cleartrip.page;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cleartrip.base.CleartripBase;
import com.cleartrip.util.Util;

public class HomePage extends CleartripBase {
	
	@FindBy(xpath="//a[text()='Flights']")
	public WebElement flightOption;

	@FindBy(xpath="//strong[text()='Round trip']")
	public WebElement roundTrip;

	@FindBy(xpath="//input[@name='origin']")
	public WebElement fromCity;

	@FindBy(xpath="//input[@name='destination']")
	public WebElement toCity;

	@FindBy(xpath="//input[@title='Depart date']")
	public WebElement departCalendar;

	@FindBy(xpath="//input[@title='Return date']")
	public WebElement returnCalendar;

	@FindBy(xpath="//a[@class='nextMonth ' ][@title='Next']")
	public WebElement nextMonth;

	@FindBy(xpath="//input[@id='SearchBtn']")
	public WebElement searchFilghts;

	@FindBy(xpath="//select[@id='Adults']")
	public WebElement numOfAdults;

	@FindBy(xpath="//select[@id='Childrens']")
	public WebElement numOfChildrens;

	@FindBy(xpath="//select[@id='Infants']")
	public WebElement numOfInfants;


	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void selectFlightOption() {
		Util.waitForElement(driver,flightOption);
		flightOption.click();
	}

	public void selectRoundTrip() {
		Util.waitForElement(driver, roundTrip);
		roundTrip.click();
	}

	public void enterFromAndToCities() {
		Util.waitForElement(driver,fromCity);
		fromCity.sendKeys(prop.getProperty("fromCity"));
		toCity.sendKeys(prop.getProperty("toCity"));
	}

	public void selectDepartReturnDate(String fromDate, String toDate) throws ParseException  {
		Util.waitForElement(driver,returnCalendar);
		Util.dateValidator(fromDate, toDate);
		departCalendar.click();	
		driver.findElement(By.xpath("//input[@id='DepartDate']")).sendKeys(fromDate);
		returnCalendar.click();
		driver.findElement(By.xpath("//input[@id='ReturnDate']")).sendKeys(toDate);
		Util.waitForElement(driver,searchFilghts);
		searchFilghts.click();
	}
	
	

}

