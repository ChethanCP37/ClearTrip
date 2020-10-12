package com.cleartrip.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cleartrip.base.CleartripBase;

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

	//tr//td[@data-month='4'][@data-year="2021"]//a[text()='21']

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}

