package com.cleartrip.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cleartrip.base.CleartripBase;
import com.cleartrip.util.Util;

public class ItineraryPage extends CleartripBase {
	Select select =null;
	
	@FindBy(xpath="//input[@value=\"Continue booking\"]")
	public WebElement continueBookingButton;

	@FindBy(xpath="//input[@title='Your email address']")
	public WebElement enterEmailId;
	
	@FindBy(xpath="//input[@id='LoginContinueBtn_1']")
	public WebElement continueButtonAfterEmail;
	
	@FindBy(xpath="//h1[contains(text(),'Bangalore → Chennai')]")
	public WebElement departRetrunCities;
	
	@FindBy(xpath="//select[@data-name='title']")
	public WebElement title;
	
	@FindBy(xpath="//input[@name='AdultFname1']")
	public WebElement firstName;

	@FindBy(xpath="(//input[@placeholder=\"Last Name\"])[1]")
	public WebElement lastName;
	
	@FindBy(xpath="(//input[@placeholder=\"Enter your mobile number\"])[1]")
	public WebElement mobileNumber;
	
	@FindBy(xpath="//input[@class='booking'][@id='travellerBtn']")
	public WebElement continueAfterPassengerDetails;
	
	@FindBy(xpath="//h2[@id='paymentTitle']")
	public WebElement paymentTitle;
	
	@FindBy(xpath="//select[@data-name='dobDay']")
	public WebElement adultBirthDay;
	
	@FindBy(xpath="//select[@data-name='dobMonth']")
	public WebElement adultBirthMonth;
	
	@FindBy(xpath="//select[@data-name='dobYear']")
	public WebElement adultBirthYear;
	
	@FindBy(xpath="//input[@data-name='nationality']")
	public WebElement nationality;
	
	public ItineraryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public boolean isDepartRetrunCitiesDisplayed() {
		Util.waitForElement(driver, departRetrunCities);
		boolean isCityDisplayed=departRetrunCities.isDisplayed();
		return isCityDisplayed;
	}
	
	public void clicnOnContinue(String emailId) {
		Util.waitForElement(driver, continueBookingButton);
		continueBookingButton.click();
		Util.waitForElement(driver, enterEmailId);
		enterEmailId.sendKeys(emailId);
		Util.waitForElement(driver, continueButtonAfterEmail);
		continueButtonAfterEmail.click();
	}
	public boolean passengerDetails() throws InterruptedException {
		Util.waitForElement(driver, firstName);
		select = new Select(title);
		select.selectByValue("Mr");
		firstName.sendKeys("FirstName");
		lastName.sendKeys("LastName");
		
		select = new Select(adultBirthDay);
		select.selectByVisibleText("7");
		
		select = new Select(adultBirthMonth);
		select.selectByVisibleText("May");
		
		select = new Select(adultBirthYear);
		select.selectByIndex(10);
		
		Util.waitForElement(driver, nationality);
		nationality.sendKeys("India");
		Actions ac= new Actions(driver);
		Thread.sleep(3000);
		ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER);
		mobileNumber.sendKeys("1000200030");
		Util.departReturnCities(driver);
		Util.waitForElement(driver, continueAfterPassengerDetails);
		continueAfterPassengerDetails.click();
		Util.waitForElement(driver, paymentTitle);
		boolean paymentTitleDisplayed=paymentTitle.isDisplayed();
		return paymentTitleDisplayed;
		
	}
}
